package cn.heyueyuan.ibranchwiki.service;

import cn.heyueyuan.ibranchwiki.entity.Content;
import cn.heyueyuan.ibranchwiki.entity.Document;
import cn.heyueyuan.ibranchwiki.entity.DocumentExample;
import cn.heyueyuan.ibranchwiki.exception.BusinessException;
import cn.heyueyuan.ibranchwiki.exception.BusinessExceptionCode;
import cn.heyueyuan.ibranchwiki.mapper.ContentMapper;
import cn.heyueyuan.ibranchwiki.mapper.DocumentMapper;
import cn.heyueyuan.ibranchwiki.mapper.DocumentMapperCust;
import cn.heyueyuan.ibranchwiki.request.DocumentQueryReq;
import cn.heyueyuan.ibranchwiki.request.DocumentSaveReq;
import cn.heyueyuan.ibranchwiki.response.DocumentQueryResp;
import cn.heyueyuan.ibranchwiki.response.PageResp;
import cn.heyueyuan.ibranchwiki.utils.CopyUtil;
import cn.heyueyuan.ibranchwiki.utils.RedisUtil;
import cn.heyueyuan.ibranchwiki.utils.RequestContext;
import cn.heyueyuan.ibranchwiki.utils.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocumentService {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

    @Resource
    private DocumentMapper documentMapper;

    @Resource
    private DocumentMapperCust documentMapperCust;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    public RedisUtil redisUtil;

//    @Resource
//    public WsService wsService;

    public List<DocumentQueryResp> all(Long ebookId) {
        DocumentExample documentExample = new DocumentExample();
        documentExample.createCriteria().andEbookIdEqualTo(ebookId);
        documentExample.setOrderByClause("sort asc");
        List<Document> documentList = documentMapper.selectByExample(documentExample);

        List<DocumentQueryResp> list = CopyUtil.copyList(documentList, DocumentQueryResp.class);

        return list;
    }

    public PageResp<DocumentQueryResp> list(DocumentQueryReq req) {
        DocumentExample documentExample = new DocumentExample();
        documentExample.setOrderByClause("sort asc");
        DocumentExample.Criteria criteria = documentExample.createCriteria();

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Document> documentList = documentMapper.selectByExample(documentExample);

        PageInfo<Document> pageInfo = new PageInfo<>(documentList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<DocumentQueryResp> list = CopyUtil.copyList(documentList, DocumentQueryResp.class);
        PageResp<DocumentQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    @Transactional
    public void save(DocumentSaveReq req) {
        Document doc = CopyUtil.copy(req, Document.class);
        Content content = CopyUtil.copy(req, Content.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            doc.setId(SnowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            documentMapper.insert(doc);

            content.setId(doc.getId());
            contentMapper.insert(content);
        } else {
            // 更新
            documentMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (count == 0) {
                contentMapper.insert(content);
            }
        }
    }

    public void delete(Long id) {
        documentMapper.deleteByPrimaryKey(id);
    }

    public void delete(List<String> ids) {
        DocumentExample docExample = new DocumentExample();
        DocumentExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        documentMapper.deleteByExample(docExample);
    }

    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        // 文档阅读数+1
        documentMapperCust.increaseViewCount(id);
        if (ObjectUtils.isEmpty(content)) {
            return "";
        } else {
            return content.getContent();
        }
    }

    public void vote(Long id) {
        // docMapperCust.increaseVoteCount(id);
        // 远程IP+doc.id作为key，24小时内不能重复
        String ip = RequestContext.getRemoteAddr();
        if (redisUtil.validateRepeat("DOC_VOTE_" + id + "_" + ip, 5000)) {
            documentMapperCust.increaseVoteCount(id);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }

        // 推送消息
        Document docDb = documentMapper.selectByPrimaryKey(id);
        String logId = MDC.get("LOG_ID");
//        wsService.sendInfo("【" + docDb.getName() + "】被点赞！", logId);
        // rocketMQTemplate.convertAndSend("VOTE_TOPIC", "【" + docDb.getName() + "】被点赞！");
    }

    public void updateEbookInfo() {
        documentMapperCust.updateEbookInfo();
    }
}
