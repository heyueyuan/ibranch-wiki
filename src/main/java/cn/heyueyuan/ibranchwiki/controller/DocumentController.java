package cn.heyueyuan.ibranchwiki.controller;

import cn.heyueyuan.ibranchwiki.request.DocumentQueryReq;
import cn.heyueyuan.ibranchwiki.request.DocumentSaveReq;
import cn.heyueyuan.ibranchwiki.response.CommonResp;
import cn.heyueyuan.ibranchwiki.response.DocumentQueryResp;
import cn.heyueyuan.ibranchwiki.response.PageResp;
import cn.heyueyuan.ibranchwiki.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Resource
    private DocumentService documentService;

    @GetMapping("/all/{ebookId}")
    public CommonResp all(@PathVariable Long ebookId) {
        CommonResp<List<DocumentQueryResp>> resp = new CommonResp<>();
        List<DocumentQueryResp> list = documentService.all(ebookId);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    public CommonResp list(@Valid DocumentQueryReq req) {
        CommonResp<PageResp<DocumentQueryResp>> resp = new CommonResp<>();
        PageResp<DocumentQueryResp> list = documentService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocumentSaveReq req) {
        CommonResp resp = new CommonResp<>();
        documentService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{idsStr}")
    public CommonResp delete(@PathVariable String idsStr) {
        CommonResp resp = new CommonResp<>();
        List<String> list = Arrays.asList(idsStr.split(","));
        documentService.delete(list);
        return resp;
    }

    @GetMapping("/find-content/{id}")
    public CommonResp findContent(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        String content = documentService.findContent(id);
        resp.setContent(content);
        return resp;
    }

    public CommonResp vote(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        documentService.vote(id);
        return resp;
    }
}
