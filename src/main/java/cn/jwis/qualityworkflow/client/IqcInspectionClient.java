//package cn.jwis.qualityworkflow.client;
//
//import cn.jwis.qualityworkflow.bean.ResultBean;
//import cn.jwis.qualityworkflow.config.FeignConfig;
//import io.swagger.annotations.ApiParam;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author xujinbiao
// * @version 0.1.0
// * @Description Iqc-inspection-service 远程调用 client
// * @create 2020-05-25 11:09
// * @since 0.1.0
// **/
//@FeignClient(name = "IqcInspectionClient", url = "${isp.iqc-inspection}"
//		, configuration = {FoundationFeignConfig.class, FeignConfig.class})
//public interface IqcInspectionClient {
//	/**
//	 * @description: //校验并导入
//	 * @author: xujinbiao
//	 * @date: 2020/5/25 11:20
//	 * @param file:
//	 * @param table:
//	 * @param validationParams:
//	 * @param request:
//	 * @return: cn.jwis.qualityworkflow.bean.ResultBean
//	 **/
//	@PostMapping(value = "/common/importWithCheck")
//	ResultBean importWithCheck(@ApiParam(value = "file") @RequestParam MultipartFile file,
//									@ApiParam(value = "table") @RequestParam String table,
//									@ApiParam(value = "validationParams") @RequestParam String validationParams,
//							  		 @RequestParam HttpServletRequest request) throws Exception;
//}
