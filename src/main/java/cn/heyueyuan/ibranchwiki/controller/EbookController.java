package cn.heyueyuan.ibranchwiki.controller;

import cn.heyueyuan.ibranchwiki.request.EbookQueryReq;
import cn.heyueyuan.ibranchwiki.request.EbookSaveReq;
import cn.heyueyuan.ibranchwiki.response.CommonResp;
import cn.heyueyuan.ibranchwiki.response.EbookQueryResp;
import cn.heyueyuan.ibranchwiki.response.PageResp;
import cn.heyueyuan.ibranchwiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService eBookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();

        PageResp<EbookQueryResp> list = eBookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        eBookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        eBookService.delete(id);
        return resp;
    }
}
