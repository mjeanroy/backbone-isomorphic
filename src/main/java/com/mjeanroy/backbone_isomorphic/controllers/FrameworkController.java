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

package com.mjeanroy.backbone_isomorphic.controllers;

import com.mjeanroy.backbone_isomorphic.models.Framework;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableMap;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FrameworkController {

	private static final List<Framework> JAVA = asList(
			new Framework(1, "Spring"),
			new Framework(2, "Play!"),
			new Framework(3, "Grails"),
			new Framework(4, "Hibernate")
	);

	private static final List<Framework> JAVASCRIPT = asList(
			new Framework(1, "Angular.js"),
			new Framework(2, "Backbone"),
			new Framework(3, "Underscore"),
			new Framework(4, "jQuery")
	);

	private static final Map<String, List<Framework>> MAP;

	static {
		Map<String, List<Framework>> map = new HashMap<String, List<Framework>>();
		map.put("java", JAVA);
		map.put("javascript", JAVASCRIPT);
		MAP = unmodifiableMap(map);
	}

	@RequestMapping(value = "/api/frameworks/{lang}", method = GET)
	public List<Framework> query(@PathVariable("lang") String lang) {
		return MAP.get(lang.toLowerCase());
	}
}
