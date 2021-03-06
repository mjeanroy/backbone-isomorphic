/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 <mickael.jeanroy@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.mjeanroy.backbone_isomorphic.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mjeanroy.springmvc.view.mustache.core.ModelAndMustacheView;
import com.mjeanroy.backbone_isomorphic.controllers.FrameworkController;
import com.mjeanroy.backbone_isomorphic.models.Framework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class FrameworkView {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FrameworkController frameworkController;

	@RequestMapping(value = "/", method = GET)
	public String frameworkView() throws JsonProcessingException {
		return "redirect:/frameworks/javascript";
	}

	@RequestMapping(value = "/frameworks/{lang}", method = GET)
	public ModelAndView frameworkView(@PathVariable("lang") String lang) throws JsonProcessingException {
		List<Framework> frameworks = frameworkController.query(lang);
		ModelAndView modelAndView = new ModelAndMustacheView("frameworks");
		modelAndView.addObject("frameworks", frameworks);
		modelAndView.addObject("lang_" + lang.toLowerCase(), true);
		modelAndView.addObject("_frameworks_", objectMapper.writeValueAsString(frameworks));
		return modelAndView;
	}
}
