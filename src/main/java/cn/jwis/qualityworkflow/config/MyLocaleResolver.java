/**
 *
 */
package cn.jwis.qualityworkflow.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

/**
 * @ClassName: MyLocaleResolver
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author longjun
 * @date 2018年11月2日
 */

public class MyLocaleResolver implements LocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale locale = Locale.getDefault();
		String language = request.getHeader("Language");
		if (locale != null && language == null) {
			return locale;
		}
		String[] split = language.split("-");
		locale = new Locale(split[0], split[1]);
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

	}
}
