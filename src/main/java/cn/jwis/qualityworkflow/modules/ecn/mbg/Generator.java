package cn.jwis.qualityworkflow.modules.ecn.mbg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 用于生产MBG的代码
 * @author 许锦标
 * @date 2020/4/20 18:58
 * @email jinbiao.xu@jwis.cn
 */
public class Generator {

	private static final Logger logger = LoggerFactory.getLogger(Generator.class);

	public static void main(String[] args) throws Exception {
		// MBG 执行过程中的警告信息
		List<String> warnings = new ArrayList<String>();
		// 当生成的代码重复时，不覆盖原代码
		boolean overwrite = false;
		// 读取我们的 MBG 配置文件
		InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		is.close();
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		// 创建 MBG
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		// 执行生成代码
		myBatisGenerator.generate(null);
		// 输出警告信息
		for (String warning : warnings) {
			logger.info(warning);
		}
	}
}
