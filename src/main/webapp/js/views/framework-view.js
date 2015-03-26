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

define(['jquery', 'underscore', 'backbone', 'js/collection/frameworks'], function ($, _, Backbone, Frameworks) {

	return Backbone.View.extend({

		initialize: function() {
			// If data is set in html
			var data = $('#_frameworks_').text();
			if (data) {
				data = JSON.parseJSON(data);
			}

			this.collection = new Frameworks();
			this.listenToOnce(this.collection, 'reset', this.render);

			if (!data) {
				this.collection.fetch();
			}
		},

		render: function () {
			var data = this.toJSON();
			var $el = this.$el;

			$.get(_.result(this, 'template'))
				.done(function (html) {
					var result = Backbone.$compile(html, data);
					$el.html(result);
				});

			return this;
		}
	});

});
