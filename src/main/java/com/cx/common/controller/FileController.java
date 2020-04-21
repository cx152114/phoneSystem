package com.cx.common.controller;

//@Controller
//@RequestMapping("/common/file")
//public class FileController {
//
//    @Autowired
//    private FileService fileService;
//
//    /**
//     * 单文件上传
//     *
//     * @param uploadFile
//     * @return
//     */
//    @PostMapping("/upload")
//    @ResponseBody
//    public R uplaod(@RequestParam("uploadFile") MultipartFile uploadFile) {
//        try {
//            return fileService.uplaod(uploadFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return R.error(e.getMessage());
//        }
//    }
//
//    /**
//     * 多文件上传
//     *
//     * @param uploadFile
//     * @param session
//     * @return
//     */
//    @PostMapping("/uploads")
//    @ResponseBody
//    public R uplaods(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
//        List<Map<String, Object>> data = new ArrayList<>();
//        for (MultipartFile f : uploadFile) {
//            try {
//                data.add(fileService.uplaod(f));
//            } catch (IOException e) {
//                e.printStackTrace();
//                data.add(R.error(e.getMessage()));
//            }
//        }
//        return R.ok().put("data", data);
//    }
//}
