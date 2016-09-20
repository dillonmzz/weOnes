! function (e) {
	if ("object" == typeof exports && "undefined" != typeof module) module.exports = e();
	else if ("function" == typeof define && define.amd) define([], e);
	else {
		var t;
		t = "undefined" != typeof window ? window : "undefined" != typeof global ? global : "undefined" != typeof self ? self : this, t.qwest = e()
	}
}(function () {
	var define, module, exports;
	return function e(t, n, o) {
		function r(a, s) {
			if (!n[a]) {
				if (!t[a]) {
					var c = "function" == typeof require && require;
					if (!s && c) return c(a, !0);
					if (i) return i(a, !0);
					var u = new Error("Cannot find module '" + a + "'");
					throw u.code = "MODULE_NOT_FOUND", u
				}
				var d = n[a] = {
					exports: {}
				};
				t[a][0].call(d.exports, function (e) {
					var n = t[a][1][e];
					return r(n ? n : e)
				}, d, d.exports, e, t, n, o)
			}
			return n[a].exports
		}
		for (var i = "function" == typeof require && require, a = 0; a < o.length; a++) r(o[a]);
		return r
	}({
		1: [function (e, t, n) {
			function o() {
				d = !1, s.length ? u = s.concat(u) : p = -1, u.length && r()
			}

			function r() {
				if (!d) {
					var e = setTimeout(o);
					d = !0;
					for (var t = u.length; t;) {
						for (s = u, u = []; ++p < t;) s && s[p].run();
						p = -1, t = u.length
					}
					s = null, d = !1, clearTimeout(e)
				}
			}

			function i(e, t) {
				this.fun = e, this.array = t
			}

			function a() {}
			var s, c = t.exports = {},
				u = [],
				d = !1,
				p = -1;
			c.nextTick = function (e) {
				var t = new Array(arguments.length - 1);
				if (arguments.length > 1)
					for (var n = 1; n < arguments.length; n++) t[n - 1] = arguments[n];
				u.push(new i(e, t)), 1 !== u.length || d || setTimeout(r, 0)
			}, i.prototype.run = function () {
				this.fun.apply(null, this.array)
			}, c.title = "browser", c.browser = !0, c.env = {}, c.argv = [], c.version = "", c.versions = {}, c.on = a, c.addListener = a, c.once = a, c.off = a, c.removeListener = a, c.removeAllListeners = a, c.emit = a, c.binding = function (e) {
				throw new Error("process.binding is not supported")
			}, c.cwd = function () {
				return "/"
			}, c.chdir = function (e) {
				throw new Error("process.chdir is not supported")
			}, c.umask = function () {
				return 0
			}
		}, {}],
		2: [function (e, t, n) {
			! function (e) {
				"use strict";
				var n = function (e) {
					var t = function (e, t, n) {
							n = "function" == typeof n ? n() : null === n ? "" : void 0 === n ? "" : n, e[e.length] = encodeURIComponent(t) + "=" + encodeURIComponent(n)
						},
						n = function (e, o, r) {
							var i, a, s;
							if ("[object Array]" === Object.prototype.toString.call(o))
								for (i = 0, a = o.length; a > i; i++) n(e + "[" + ("object" == typeof o[i] ? i : "") + "]", o[i], r);
							else if (o && "[object Object]" === o.toString())
								for (s in o) o.hasOwnProperty(s) && (e ? n(e + "[" + s + "]", o[s], r, t) : n(s, o[s], r, t));
							else if (e) t(r, e, o);
							else
								for (s in o) t(r, s, o[s]);
							return r
						};
					return n("", e, []).join("&").replace(/%20/g, "+")
				};
				"object" == typeof t && "object" == typeof t.exports ? t.exports = n : "function" == typeof define && define.amd ? define([], function () {
					return n
				}) : e.param = n
			}(this)
		}, {}],
		3: [function (e, t, n) {
			(function (e) {
				! function (t) {
					function n(e) {
						return "function" == typeof e
					}

					function o(e) {
						return "object" == typeof e
					}

					function r(t) {
						"undefined" != typeof setImmediate ? setImmediate(t) : "undefined" != typeof e && e.nextTick ? e.nextTick(t) : setTimeout(t, 0)
					}
					var i;
					t[0][t[1]] = function a(e) {
						var t, s = [],
							c = [],
							u = function (e, n) {
								return null == t && null != e && (t = e, s = n, c.length && r(function () {
									for (var e = 0; e < c.length; e++) c[e]()
								})), t
							};
						return u.then = function (u, d) {
							var p = a(e),
								l = function () {
									function e(t) {
										var r, a = 0;
										try {
											if (t && (o(t) || n(t)) && n(r = t.then)) {
												if (t === p) throw new TypeError;
												r.call(t, function () {
													a++ || e.apply(i, arguments)
												}, function (e) {
													a++ || p(!1, [e])
												})
											} else p(!0, arguments)
										} catch (s) {
											a++ || p(!1, [s])
										}
									}
									try {
										var r = t ? u : d;
										n(r) ? e(r.apply(i, s || [])) : p(t, s)
									} catch (a) {
										p(!1, [a])
									}
								};
							return null != t ? r(l) : c.push(l), p
						}, e && (u = e(u)), u
					}
				}("undefined" == typeof t ? [window, "pinkySwear"] : [t, "exports"])
			}).call(this, e("_process"))
		}, {
			_process: 1
		}],
		qwest: [function (_dereq_, module, exports) {
			module.exports = function () {
				var global = window || this,
					pinkyswear = _dereq_("pinkyswear"),
					jparam = _dereq_("jquery-param"),
					defaultXdrResponseType = "json",
					limit = null,
					requests = 0,
					request_stack = [],
					getXHR = function () {
						return global.XMLHttpRequest ? new global.XMLHttpRequest : new ActiveXObject("Microsoft.XMLHTTP")
					},
					xhr2 = "" === getXHR().responseType,
					qwest = function (method, url, data, options, before) {
						method = method.toUpperCase(), data = data || null, options = options || {};
						var nativeResponseParsing = !1,
							crossOrigin, xhr, xdr = !1,
							timeoutInterval, aborted = !1,
							attempts = 0,
							headers = {},
							mimeTypes = {
								text: "*/*",
								xml: "text/xml",
								json: "application/json",
								post: "application/x-www-form-urlencoded"
							},
							accept = {
								text: "*/*",
								xml: "application/xml; q=1.0, text/xml; q=0.8, */*; q=0.1",
								json: "application/json; q=1.0, text/*; q=0.8, */*; q=0.1"
							},
							vars = "",
							i, j, serialized, response, sending = !1,
							delayed = !1,
							timeout_start, promise = pinkyswear(function (e) {
								if (e["catch"] = function (t) {
										return e.then(null, t)
									}, e.complete = function (t) {
										return e.then(t, t)
									}, "pinkyswear" in options)
									for (i in options.pinkyswear) e[i] = options.pinkyswear[i];
								return e.send = function () {
									if (!sending) {
										if (requests == limit) return void request_stack.push(e);
										if (++requests, sending = !0, timeout_start = (new Date).getTime(), xhr = getXHR(), crossOrigin && ("withCredentials" in xhr || !global.XDomainRequest || (xhr = new XDomainRequest, xdr = !0, "GET" != method && "POST" != method && (method = "POST"))), xdr ? xhr.open(method, url) : (xhr.open(method, url, options.async, options.user, options.password), xhr2 && options.async && (xhr.withCredentials = options.withCredentials)), !xdr)
											for (var t in headers) headers[t] && xhr.setRequestHeader(t, headers[t]);
										if (xhr2 && "document" != options.responseType && "auto" != options.responseType) try {
											xhr.responseType = options.responseType, nativeResponseParsing = xhr.responseType == options.responseType
										} catch (n) {}
										xhr2 || xdr ? (xhr.onload = handleResponse, xhr.onerror = handleError) : xhr.onreadystatechange = function () {
											4 == xhr.readyState && handleResponse()
										}, "auto" != options.responseType && "overrideMimeType" in xhr && xhr.overrideMimeType(mimeTypes[options.responseType]), before && before(xhr), xdr ? setTimeout(function () {
											xhr.send("GET" != method ? data : null)
										}, 0) : xhr.send("GET" != method ? data : null)
									}
								}, e
							}),
							handleResponse = function () {
								var i, responseType;
								if (--requests, sending = !1, (new Date).getTime() - timeout_start >= options.timeout) return void(options.attempts && ++attempts == options.attempts ? promise(!1, [xhr, response, new Error("Timeout (" + url + ")")]) : promise.send());
								request_stack.length && request_stack.shift().send();
								try {
									if (nativeResponseParsing && "response" in xhr && null !== xhr.response) response = xhr.response;
									else if ("document" == options.responseType) {
										var frame = document.createElement("iframe");
										frame.style.display = "none", document.body.appendChild(frame), frame.contentDocument.open(), frame.contentDocument.write(xhr.response), frame.contentDocument.close(), response = frame.contentDocument, document.body.removeChild(frame)
									} else {
										if (responseType = options.responseType, "auto" == responseType)
											if (xdr) responseType = defaultXdrResponseType;
											else {
												var ct = xhr.getResponseHeader("Content-Type") || "";
												responseType = ct.indexOf(mimeTypes.json) > -1 ? "json" : ct.indexOf(mimeTypes.xml) > -1 ? "xml" : "text"
											}
										switch (responseType) {
											case "json":
												try {
													response = "JSON" in global ? JSON.parse(xhr.responseText) : eval("(" + xhr.responseText + ")")
												} catch (e) {
													throw "Error while parsing JSON body : " + e
												}
												break;
											case "xml":
												try {
													global.DOMParser ? response = (new DOMParser).parseFromString(xhr.responseText, "text/xml") : (response = new ActiveXObject("Microsoft.XMLDOM"), response.async = "false", response.loadXML(xhr.responseText))
												} catch (e) {
													response = void 0
												}
												if (!response || !response.documentElement || response.getElementsByTagName("parsererror").length) throw "Invalid XML";
												break;
											default:
												response = xhr.responseText
										}
									}
									if ("status" in xhr && !/^2|1223/.test(xhr.status)) throw xhr.status + " (" + xhr.statusText + ")";
									promise(!0, [xhr, response])
								} catch (e) {
									promise(!1, [xhr, response, e])
								}
							},
							handleError = function (e) {
								--requests, promise(!1, [xhr, null, new Error("Connection aborted")])
							};
						switch (options.async = "async" in options ? !!options.async : !0, options.cache = "cache" in options ? !!options.cache : !1, options.dataType = "dataType" in options ? options.dataType.toLowerCase() : "post", options.responseType = "responseType" in options ? options.responseType.toLowerCase() : "auto", options.user = options.user || "", options.password = options.password || "", options.withCredentials = !!options.withCredentials, options.timeout = "timeout" in options ? parseInt(options.timeout, 10) : 3e4, options.attempts = "attempts" in options ? parseInt(options.attempts, 10) : 1, i = url.match(/\/\/(.+?)\//), crossOrigin = i && (i[1] ? i[1] != location.host : !1), "ArrayBuffer" in global && data instanceof ArrayBuffer ? options.dataType = "arraybuffer" : "Blob" in global && data instanceof Blob ? options.dataType = "blob" : "Document" in global && data instanceof Document ? options.dataType = "document" : "FormData" in global && data instanceof FormData && (options.dataType = "formdata"), options.dataType) {
							case "json":
								data = JSON.stringify(data);
								break;
							case "post":
								data = jparam(data)
						}
						if (options.headers) {
							var format = function (e, t, n) {
								return t + n.toUpperCase()
							};
							for (i in options.headers) headers[i.replace(/(^|-)([^-])/g, format)] = options.headers[i]
						}
						return "Content-Type" in headers || "GET" == method || options.dataType in mimeTypes && mimeTypes[options.dataType] && (headers["Content-Type"] = mimeTypes[options.dataType]), headers.Accept || (headers.Accept = options.responseType in accept ? accept[options.responseType] : "*/*"), crossOrigin || "X-Requested-With" in headers || (headers["X-Requested-With"] = "XMLHttpRequest"), options.cache || "Cache-Control" in headers || (headers["Cache-Control"] = "no-cache"), "GET" == method && data && (vars += data), vars && (url += (/\?/.test(url) ? "&" : "?") + vars), options.async && promise.send(), promise
					};
				return {
					base: "",
					get: function (e, t, n, o) {
						return qwest("GET", this.base + e, t, n, o)
					},
					post: function (e, t, n, o) {
						return qwest("POST", this.base + e, t, n, o)
					},
					put: function (e, t, n, o) {
						return qwest("PUT", this.base + e, t, n, o)
					},
					"delete": function (e, t, n, o) {
						return qwest("DELETE", this.base + e, t, n, o)
					},
					map: function (e, t, n, o, r) {
						return qwest(e.toUpperCase(), this.base + t, n, o, r)
					},
					xhr2: xhr2,
					limit: function (e) {
						limit = e
					},
					setDefaultXdrResponseType: function (e) {
						defaultXdrResponseType = e.toLowerCase()
					}
				}
			}()
		}, {
			"jquery-param": 2,
			pinkyswear: 3
		}]
	}, {}, [2, 3])("qwest")
}),
function (e) {
	function t() {
		try {
			return a in e && e[a]
		} catch (t) {
			return !1
		}
	}

	function n(e) {
		return e.replace(/^d/, "___$&").replace(l, "___")
	}
	var o, r = {},
		i = e.document,
		a = "localStorage",
		s = "script";
	if (r.disabled = !1, r.version = "1.3.17", r.set = function (e, t) {}, r.get = function (e, t) {}, r.has = function (e) {
			return void 0 !== r.get(e)
		}, r.remove = function (e) {}, r.clear = function () {}, r.transact = function (e, t, n) {
			null == n && (n = t, t = null), null == t && (t = {});
			var o = r.get(e, t);
			n(o), r.set(e, o)
		}, r.getAll = function () {}, r.forEach = function () {}, r.serialize = function (e) {
			return JSON.stringify(e)
		}, r.deserialize = function (e) {
			if ("string" != typeof e) return void 0;
			try {
				return JSON.parse(e)
			} catch (t) {
				return e || void 0
			}
		}, t()) o = e[a], r.set = function (e, t) {
		return void 0 === t ? r.remove(e) : (o.setItem(e, r.serialize(t)), t)
	}, r.get = function (e, t) {
		var n = r.deserialize(o.getItem(e));
		return void 0 === n ? t : n
	}, r.remove = function (e) {
		o.removeItem(e)
	}, r.clear = function () {
		o.clear()
	}, r.getAll = function () {
		var e = {};
		return r.forEach(function (t, n) {
			e[t] = n
		}), e
	}, r.forEach = function (e) {
		for (var t = 0; t < o.length; t++) {
			var n = o.key(t);
			e(n, r.get(n))
		}
	};
	else if (i.documentElement.addBehavior) {
		var c, u;
		try {
			u = new ActiveXObject("htmlfile"), u.open(), u.write("<" + s + ">document.w=window</" + s + '><iframe src="/favicon.ico"></iframe>'), u.close(), c = u.w.frames[0].document, o = c.createElement("div")
		} catch (d) {
			o = i.createElement("div"), c = i.body
		}
		var p = function (e) {
				return function () {
					var t = Array.prototype.slice.call(arguments, 0);
					t.unshift(o), c.appendChild(o), o.addBehavior("#default#userData"), o.load(a);
					var n = e.apply(r, t);
					return c.removeChild(o), n
				}
			},
			l = new RegExp("[!\"#$%&'()*+,/\\\\:;<=>?@[\\]^`{|}~]", "g");
		r.set = p(function (e, t, o) {
			return t = n(t), void 0 === o ? r.remove(t) : (e.setAttribute(t, r.serialize(o)), e.save(a), o)
		}), r.get = p(function (e, t, o) {
			t = n(t);
			var i = r.deserialize(e.getAttribute(t));
			return void 0 === i ? o : i
		}), r.remove = p(function (e, t) {
			t = n(t), e.removeAttribute(t), e.save(a)
		}), r.clear = p(function (e) {
			var t = e.XMLDocument.documentElement.attributes;
			e.load(a);
			for (var n, o = 0; n = t[o]; o++) e.removeAttribute(n.name);
			e.save(a)
		}), r.getAll = function (e) {
			var t = {};
			return r.forEach(function (e, n) {
				t[e] = n
			}), t
		}, r.forEach = p(function (e, t) {
			for (var n, o = e.XMLDocument.documentElement.attributes, i = 0; n = o[i]; ++i) t(n.name, r.deserialize(e.getAttribute(n.name)))
		})
	}
	try {
		var f = "__storejs__";
		r.set(f, f), r.get(f) != f && (r.disabled = !0), r.remove(f)
	} catch (d) {
		r.disabled = !0
	}
	r.enabled = !r.disabled, "undefined" != typeof module && module.exports && this.module !== module ? module.exports = r : "function" == typeof define && define.amd ? define(r) : e.store = r
}(Function("return this")()),
function (e) {
	var t, n, o, r, i, a, s, c, u, d, p, l, f, h = function (e, t) {
			var n = document.createEvent("CustomEvent");
			return n.initCustomEvent(e, t.bubbles, t.cancelable, t.detail), n
		},
		m = function () {
			return +new Date
		},
		y = function (e) {
			return e.pageX || e.touches[0].pageX
		},
		g = function (e) {
			return e.pageY || e.touches[0].pageY
		};
	f = {
		tapThreshold: {
			move: 5,
			time: 300
		},
		sweepThreshold: {
			move: 5
		}
	}, u = function (e) {
		c = m(), n = r = y(e), o = i = g(e), t = e.target
	}, d = function (e) {
		r = y(e), i = g(e)
	}, l = p = function (e) {
		var u, d, p, l, y, g, v, w, x, b, T;
		if (u = m() - c, a = r - n, s = i - o, d = Math.abs(a), p = Math.abs(s), l = Math.max(d, p), u <= f.tapThreshold.time && l <= f.tapThreshold.move ? (y = !0, T = "tap") : l > f.sweepThreshold.move && (g = !0, d > p ? a > 0 ? (w = !0, T = "sweepright") : (v = !0, T = "sweepleft") : s > 0 ? (b = !0, T = "sweepdown") : (x = !0, T = "sweepup")), T && t) {
			var k = new h(T, {
				bubbles: !0,
				cancelable: !0
			});
			t.dispatchEvent(k)
		}
	};
	var v = function (t, n, o) {
		t && (e.attachEvent ? t.attachEvent("on" + n, o) : t.addEventListener(n, o, !1))
	};
	"ontouchstart" in e ? (window.addEventListener("touchstart", u), window.addEventListener("touchmove", d), window.addEventListener("touchend", p)) : v(e, "click", function (e) {
		var t = e || window.event,
			n = t.target;
		if (n) {
			var o = new h("tap", {
				bubbles: !0,
				cancelable: !0
			});
			n.dispatchEvent(o)
		}
	}), "ontouchcancel" in e && window.addEventListener("touchcancel", l)
}(window),
function (e) {
	e.Browser = {};
	var t = navigator.userAgent.toLowerCase(),
		n = t.match(/(opera|ie|firefox|chrome|trident|crios|version)[\s\/:]([\w\d\.]+)?.*?(safari|(?:rv[\s\/:]|version[\s\/:])([\w\d\.]+)|$)/) || [null, "unknown", 0];
	"trident" == n[1] ? (n[1] = "ie", n[4] && (n[2] = n[4])) : "crios" == n[1] && (n[1] = "chrome");
	var o;
	o = t.match(/ip(?:ad|od|hone)/) ? "ios" : (o = t.match(/(?:webos|android)/)) ? o[0] : (o = t.match(/mac|win|linux/)) ? o[0] : "other", "win" == o && (o = "windows");
	var r = /micromessenger/.test(t) ? "wechat" : /qq/.test(t) ? "qq" : /weibo/.test(t) ? "weibo" : /com\.xisue\.zhoumo/.test(t) ? "xisue" : "";
	Browser.name = "version" == n[1] ? n[3] : n[1], Browser.version = parseFloat("opera" == n[1] && n[4] ? n[4] : n[2]), Browser.platform = o, Browser.app = r, Browser.name = "version" == n[1] ? n[3] : n[1], Browser.version = parseFloat("opera" == n[1] && n[4] ? n[4] : n[2]), Browser.platform = o, Browser.app = r;
	try {
		if ("xisue" == r) {
			var i = t.match(/com\.xisue\.zhoumo(?:\.devel)?\/(\d)\.(\d)(?:\.(\d))?/);
			i && (Browser.appv_1 = i[1] || null, Browser.appv_2 = i[2] || null, Browser.appv_3 = i[3] || null)
		}
	} catch (a) {
		console.log("xisue ua 正则式需调整")
	}
	var s = window.getComputedStyle(document.documentElement, ""),
		c = "";
	try {
		c = Array.prototype.slice.call(s).join("")
	} catch (u) {
		for (var d = 0, p = s.length; p > d; d++) c += s[d]
	}
	var l = (c.match(/-(moz|webkit|ms)-/) || "" === s.OLink && ["", "o"])[1];
	Browser.cssPrefix = "-" + l + "-", Browser.transform = l[0].toUpperCase() + l.substr(1) + "Transform";
	var f = document.createElement("div"),
		h = {
			WebkitTransition: "webkitTransitionEnd",
			MozTransition: "transitionend",
			OTransition: "oTransitionEnd otransitionend",
			transition: "transitionend"
		};
	for (var m in h)
		if (void 0 !== f.style[m]) {
			Browser.transEndName = h[m];
			break
		}
}(window),
function (e) {
	var t = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j"],
		n = e.utils = {
			n2s: function (e) {
				e += "", e = e.split("");
				var n = [];
				return e.forEach(function (e, o) {
					n.push(t[e])
				}), n.join("")
			},
			s2n: function (e) {
				e += "", e = e.split("");
				var n = [];
				return e.forEach(function (e, o) {
					n.push(t.indexOf(e))
				}), n.join("")
			},
			round2: function (e, t) {
				e = e || 0, t = t || 2;
				var n = Math.pow(10, t);
				return Math.floor(e * n * 10) % 5 == 0 && Math.floor(e * n * 10) == e * n * 10 && Math.floor(e * n) % 2 == 0 ? Math.floor(e * n) / n : Math.round(e * n) / n
			},
			obj2qs: function (e) {
				e = e || {};
				var t = "?";
				return $.each(e, function (e, n) {
					e && n && (t += e + "=" + n + "&")
				}), t.length > 1 ? t : ""
			},
			linkConvert: function (e, t, o) {
				var r, i = "";
				return r = n.parseQuery(e), /^http/.test(e) ? i = e : /^inapp:\/\/actlist/.test(e) ? t ? (i = "/" + t, /\d+/.test(r.genre_id) ? (i += "/category/" + r.genre_id, delete r.genre_id) : i += "/hot", i += n.obj2qs(r)) : i = "/category" + n.obj2qs(r) : /^inapp:\/\/main/.test(e) ? i = "/search" : /^inapp:\/\/act/.test(e) && /\d+/.test(r.id) ? i = "/huodong/" + r.id : /^inapp:\/\/topic/.test(e) && /\d+/.test(r.id) ? i = "/zhuanti/" + r.id : /^inapp:\/\/marketing/.test(e) && /\d+/.test(r.id) ? i = "/marketing/" + r.id : /^inapp:\/\/(user|order|shop|login|topiclist|calendar|dailylist|nearby)/.test(e) ? i = e : (i = e.replace(/^inapp/, "http"), /^http/.test(i) || (i = "http://" + i)), "undefined" != typeof sticky && "" != sticky && (i += i.indexOf("?") > -1 ? "&" + sticky : "?" + sticky), i
			},
			parseQuery: function (e) {
				var t, n = {};
				if (e = e || location.search || "", t = e.indexOf("?"), e = t > -1 ? e.slice(t + 1) : "") {
					e = e.split("&");
					var o, r, i, a, s;
					for (o = 0, r = e.length; r > o; o++) i = e[o], i = i.split("="), a = i[0], s = i[1], n[a] = s
				}
				return n
			},
			stickyParams: ["paysource", "zmsource", "cmbsource", "hdl", "cooper"],
			blockedParams: ["UUID", "top_session"],
			getSticky: function (e) {
				var t, o = "";
				return t = Object.keys(e), t.forEach(function (t) {
					n.stickyParams.indexOf(t) > -1 && (o += "&" + t + "=" + e[t])
				}), o.replace(/^&/, "")
			},
			isPassport: function (e) {
				var t = /[0-9a-zA-Z]{4,32}/;
				return t.test(e) ? !0 : !1
			},
			isMobile: function (e) {
				var t = /^1[34587]\d{9}$/;
				return t.test(e) ? !0 : !1
			},
			isNumber: function (e, t) {
				var n;
				return n = t ? new RegExp("^\\d{" + t + "}$") : /^\d+$/, n.test(e) ? !0 : !1
			},
			isEmail: function (e) {
				var t = /^(?:[-\w])+(?:\.[-\w]+)*@(?:[-\w])+(?:(?:\.[-\w]+)+)$/;
				return e && t.test(e) ? !0 : !1
			},
			showMsg: function (e, t) {
				var n = $("<div>");
				if ("ios" == Browser.platform) {
					var o = $(window).scrollTop() + 100;
					n.attr("class", "zw-tossMsg-top").css("top", o).text(e)
				} else n.attr("class", "zw-tossMsg").text(e);
				return $("body").append(n), t ? setTimeout(function () {
					n.remove()
				}, t) : n
			},
			showMsgDelay: function (e, t) {
				setTimeout(function () {
					n.showMsg(e, t)
				}, 100)
			},
			getBeginDate: function () {
				var e = new Date,
					t = e.getFullYear(),
					o = n.zeroPadding(e.getMonth() + 1, 2),
					r = n.zeroPadding(e.getDate(), 2);
				return t + "-" + o + "-" + r
			},
			getEndDate: function (e) {
				var t, o, r, e = e || 3,
					i = new Date;
				return o = i.getMonth(), i.setMonth(o + e), t = i.getFullYear(), o = n.zeroPadding(i.getMonth() + 1, 2), r = n.zeroPadding(i.getDate(), 2), t + "-" + o + "-" + r
			},
			parseDate: function (e) {
				var t, n, o, r, i, a = e,
					s = /(\d+)-(\d+)-(\d+)/,
					c = {};
				for (r = 0, i = a.length; i > r; r++) t = a[r].match(s), t && (n = t[1] + t[2], o = parseInt(t[3]) + "", void 0 === c[n] && (c[n] = []), c[n].push(o));
				return c
			},
			getLocalDateString: function (e) {
				var t, n, o, r, i, a, s, c, u;
				return c = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], n = new Date, o = /(\d+)-(\d+)-(\d+)/, a = e.match(o), r = a[1], i = a[2] - 0, s = a[3] - 0, t = "" + r + "年", t += i + "月", t += s + "日", n = new Date(r, i - 1, s), u = n.getDay(), t += " " + c[u]
			},
			zeroPadding: function (e, t) {
				var n, o, r = "";
				if (e += "", e.length < t)
					for (o = t - e.length, n = 0; o > n; n++) r += "0" + e;
				else r = e;
				return r
			},
			makeArray: function (e) {
				for (var t = [], n = 0, o = e.length; o > n; n++) t.push(e[n]);
				return t
			},
			isIdCard: function (e) {
				var t = /(^\d{15}$)|(^\d{17}([0-9]|[Xx])$)/;
				return t.test(e) ? !0 : !1
			},
			json: {
				encode: function (e) {
					var t;
					if ("string" != typeof e) try {
						t = JSON.stringify(e)
					} catch (n) {}
					return t ? t : e
				},
				decode: function (e) {
					var t;
					if ("string" == typeof e) try {
						t = JSON.parse(e)
					} catch (n) {}
					return t ? t : e
				}
			},
			timestamp: function () {
				return Math.round(new Date / 1e3)
			},
			jstimestamp: function () {
				return +new Date
			},
			getuid: function (e) {
				"undefined" == typeof e && (e = 10);
				for (var t = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", n = "", o = 0; e > o;) n += t[Math.floor(Math.random() * (t.length - 1) + 1)], o++;
				return n
			},
			getRandomNumber: function (e) {
				(!e || e > 16) && (e = 6);
				var t = new RegExp("0\\.0*(\\d{" + e + "})");
				return (Math.random() + "").match(t)[1]
			},
			range: function (e, t) {
				for (var n = [], t = t || 1, o = 0; e > o; o++) n[o] = "undefined" == typeof n[o - 1] ? 0 : n[o - 1] + t;
				return n
			},
			random: function (e, t) {
				return "undefined" == typeof t && (t = e, e = 0), Math.floor(e + (t - e) * Math.random())
			}
		};
	"function" != typeof Array.prototype.shuffle && (Array.prototype.shuffle = function () {
		var e, t, o, r, i, a, s = this,
			c = [];
		for (e = 0, t = s.length; t > e; e++) o = s[e], i = c.length, r = n.random(i), a = c[r], c[r] = o, "undefined" != typeof a && (c[i] = a);
		return c
	})
}("undefined" != typeof window ? window : this),
function (e) {
	e.CON = {
		localkey: "zwlocaldata",
		localgeo: "zwgeolocation",
		localhc: "zwhotcity",
		localcl: "zwpoilist",
		update: "update",
		actcity: "zwactivecity",
		usidkey: "zwusid"
	}
}("undefined" != typeof window ? window : this),
function (e, t) {
	"use strict";
	var n = function (e) {
			if ("object" != typeof e.document) throw new Error("Cookies.js requires a `window` with a `document` object");
			var n = function (e, t, o) {
				return 1 === arguments.length ? n.get(e) : n.set(e, t, o)
			};
			return n._document = e.document, n._cacheKeyPrefix = "cookey.", n._maxExpireDate = new Date("Fri, 31 Dec 9999 23:59:59 UTC"), n.defaults = {
				path: "/",
				secure: !1
			}, n.get = function (e) {
				n._cachedDocumentCookie !== n._document.cookie && n._renewCache();
				var o = n._cache[n._cacheKeyPrefix + e];
				return o === t ? t : decodeURIComponent(o)
			}, n.set = function (e, o, r) {
				return r = n._getExtendedOptions(r), r.expires = n._getExpiresDate(o === t ? -1 : r.expires), n._document.cookie = n._generateCookieString(e, o, r), n
			}, n.expire = function (e, o) {
				return n.set(e, t, o)
			}, n._getExtendedOptions = function (e) {
				return {
					path: e && e.path || n.defaults.path,
					domain: e && e.domain || n.defaults.domain,
					expires: e && e.expires || n.defaults.expires,
					secure: e && e.secure !== t ? e.secure : n.defaults.secure
				}
			}, n._isValidDate = function (e) {
				return "[object Date]" === Object.prototype.toString.call(e) && !isNaN(e.getTime())
			}, n._getExpiresDate = function (e, t) {
				if (t = t || new Date, "number" == typeof e ? e = e === 1 / 0 ? n._maxExpireDate : new Date(t.getTime() + 1e3 * e) : "string" == typeof e && (e = new Date(e)), e && !n._isValidDate(e)) throw new Error("`expires` parameter cannot be converted to a valid Date instance");
				return e
			}, n._generateCookieString = function (e, t, n) {
				e = e.replace(/[^#$&+\^`|]/g, encodeURIComponent), e = e.replace(/\(/g, "%28").replace(/\)/g, "%29"), t = (t + "").replace(/[^!#$&-+\--:<-\[\]-~]/g, encodeURIComponent), n = n || {};
				var o = e + "=" + t;
				return o += n.path ? ";path=" + n.path : "", o += n.domain ? ";domain=" + n.domain : "", o += n.expires ? ";expires=" + n.expires.toUTCString() : "", o += n.secure ? ";secure" : ""
			}, n._getCacheFromString = function (e) {
				for (var o = {}, r = e ? e.split("; ") : [], i = 0; i < r.length; i++) {
					var a = n._getKeyValuePairFromCookieString(r[i]);
					o[n._cacheKeyPrefix + a.key] === t && (o[n._cacheKeyPrefix + a.key] = a.value)
				}
				return o
			}, n._getKeyValuePairFromCookieString = function (e) {
				var t = e.indexOf("=");
				t = 0 > t ? e.length : t;
				var n, o = e.substr(0, t);
				try {
					n = decodeURIComponent(o)
				} catch (r) {
					console && "function" == typeof console.error && console.error('Could not decode cookie with key "' + o + '"', r)
				}
				return {
					key: n,
					value: e.substr(t + 1)
				}
			}, n._renewCache = function () {
				n._cache = n._getCacheFromString(n._document.cookie), n._cachedDocumentCookie = n._document.cookie
			}, n._areEnabled = function () {
				var e = "cookies.js",
					t = "1" === n.set(e, 1).get(e);
				return n.expire(e), t
			}, n.enabled = n._areEnabled(), n
		},
		o = "object" == typeof e.document ? n(e) : n;
	"function" == typeof define && define.amd ? define(function () {
		return o
	}) : "object" == typeof exports ? ("object" == typeof module && "object" == typeof module.exports && (exports = module.exports = o), exports.Cookies = o) : e.Cookies = o
}("undefined" == typeof window ? this : window),
function (e) {
	var t = (e.getGeolocation = function (e) {
			var t, n;
			navigator.geolocation ? (navigator.geolocation.getCurrentPosition(function (o) {
				var r = {
					lat: o.coords.latitude,
					lon: o.coords.longitude
				};
				t || (e(null, r), n && (clearTimeout(n), n = null))
			}, function (o) {
				var r = {
					1: "Authorization fails",
					2: "Can't detect your location",
					3: "Connection timeout"
				};
				t || (e({
					code: o.code,
					message: r[o.code]
				}), n && (clearTimeout(n), n = null))
			}, {
				enableHighAccuray: !1
			}), n = setTimeout(function () {
				e({
					code: 4,
					message: "timeout"
				}), t = !0, n = null
			}, 5e3)) : e({
				code: 0,
				message: "no support"
			})
		}, e.getCity = function (e, t, n) {
			qwest.get("/proxy", {
				api_path: "/other/lonlataddress",
				fields_version: "3.3",
				v: "3.0",
				lon: e,
				lat: t
			}, function () {}).then(function (o, r) {
				var i = utils.json.decode(r);
				if (i && "success" == i.status) {
					var a = i.result;
					a.lon = e, a.lat = t, n(null, a)
				} else n(utils.json.encode(r))
			})["catch"](function (e, t, o) {
				n(utils.json.encode(o))
			}).complete(function (e, t) {})
		}),
		n = e.updatePosition = function (e) {
			t("", "", e)
		};
	e.correctPosition = function () {
		n(function (e, t) {
			e || (t.lat && t.lon && Cookies.set("lon", t.lon).set("lat", t.lat), Cookies.set("city_id", t.city_id).set("city", t.city).set("city_pinyin", t.pinyin))
		})
	}
}("undefined" != typeof window ? window : this),
function (e, t, n) {
	var o = function (t) {
		if ("img" == t.tagName.toLowerCase()) return t.src;
		var n = e(t).css("backgroundImage"),
			o = n.match(/url\((.*)\)/);
		return o ? o[1] : ""
	};
	e.fn.zwSlide = function (t, r) {
		var i, a, s, c, u, d, p, l, f, h, m, y, g, v, w, x, t = t || ".zw-gp",
			b = e(this),
			T = 15,
			k = n.cssPrefix,
			E = n.transform,
			C = function () {
				var e = a[0].style[E].match(/translate3d\(([^,]*)/),
					t = e ? e[1] : 0;
				return parseInt(t, 10)
			},
			_ = function (e) {
				var t = e ? 0 > m ? "ceil" : "floor" : "round";
				c = Math[t](C() / (l / a.children().length)), c += e, c = Math.min(c, 0), c = Math.max(-(a.children().length - 1), c)
			},
			D = function (e) {
				e.preventDefault(), e.stopPropagation(), i = void 0, x = 1, v = -(a.children().length - 1), w = +new Date, f = e.touches[0].pageX, h = e.touches[0].pageY, m = 0, y = 0, _(0), a[0].style[k + "transition-duration"] = 0
			},
			q = function (e) {
				e.touches.length > 1 || (m = e.touches[0].pageX - f, y = e.touches[0].pageY - h, f = e.touches[0].pageX, h = e.touches[0].pageY, i = !0, g = m / x + C(), e.preventDefault(), x = 0 === c && m > 0 ? f / s + 1.25 : c === v && 0 > m ? Math.abs(f) / s + 1.25 : 1, a[0].style[E] = "translate3d(" + g + "px,0,0)")
			},
			j = function (e) {
				return i ? (_(+new Date - w < 1e3 && Math.abs(m) > 15 ? 0 > m ? -1 : 1 : 0), g = c * s, a[0].style[k + "transition-duration"] = ".2s", void(a[0].style[E] = "translate3d(" + g + "px,0,0)")) : void a.parent().remove()
			};
		r = e(r);
		var z = b.find(t),
			M = z.index(r);
		d = e("body").width(), p = screen.availHeight, s = d + T, l = s * z.length, u = '<div class="zw-slider-view" style="width:' + d + "px;height:100%;position:fixed;left:50%;top:0;background:#000000;overflow:hidden;margin-left:-" + d / 2 + 'px"><div class="zw-slider-group" style="position:absolute;left:0;top:0;width:' + l + "px;height:" + p + 'px;">', z.each(function () {
			e(this);
			u += "<div style='width:" + d + "px;margin-right:" + T + "px;height:" + p + "px;float:left;background-repeat:no-repeat;background-position:center;background-size:contain;background-image:url(" + o(this) + ");' ></div>"
		}), u += "</div></div>";
		var O = e(u);
		a = O.children();
		var A = -(M * s);
		a[0].style[E] = "translate3d(" + A + "px,0,0)", e("body").append(O), O[0].addEventListener("touchmove", q), O[0].addEventListener("touchstart", D), O[0].addEventListener("touchend", j), O[0].addEventListener("touchcancel", j), window.ontouchstart || O.on("tap", function () {
			a.parent().remove()
		})
	}
}(jQuery, window, Browser),
function (e, t) {
	e.fn.zwLazyload = function (n, o) {
		n = n ? n : "[data-src]", this.each(function () {
			var r, i = e(this),
				a = function () {
					if (!i.is(":hidden") && (r = i.find(n), r.length > 0)) {
						var o = e(t).height(),
							a = e(t).scrollTop();
						r.each(function (t, n) {
							n = e(n);
							var r = n.offset().top,
								i = r + n.height();
							if (r - a > 0 && o > r - a || i - a > 0 && o > i - a) {
								var s = n.attr("data-src");
								"img" == this.tagName.toLowerCase() ? n.attr("src", s) : n.css("backgroundImage", "url(" + s + ")"), n.removeAttr("data-src")
							}
						})
					}
				};
			o || e(t).bind("scroll", a), a()
		})
	}
}(jQuery, window),
function (e) {
	function t() {
		var e = document.createElement("iframe");
		e.style.cssText = "display:none;width:0px;height:0px;", document.body.appendChild(e), o = e
	}
	var n = {
			chromeIntent: "intent://#Intent;scheme=xishzmq;package=com.xisue.zhoumo;end",
			ios: "wb3903716518://zmqne",
			webIos: "itms-apps://itunes.apple.com/app/id820846113",
			webAndroid: "http://wanzhoumo.com/download?type=2&apk=A2",
			android: "xishzmq://",
			wechat: "http://a.app.qq.com/o/simple.jsp?pkgname=com.xisue.zhoumo",
			web: "http://wanzhoumo.com/",
			h5Activity: location.protocol + "//" + location.host + "/shanghai/huodong/",
			h5search: location.protocol + "//" + location.host + "/m/search/index?type=a&china=1&pinyin=lasa&keyword="
		},
		o = null,
		r = function (e) {
			var t = e || {},
				r = navigator.userAgent;
			if (isIPhone = null != r.match(/iPhone|iPod/i), isIPad = null != r.match(/iPad/i), isAndroid = null != r.match(/Android/i), isChrome = r.match(/Chrome\/([\d.]+)/) || r.match(/CriOS\/([\d.]+)/), wechat = r.toLowerCase().match(/micromessenger/), qq = r.toLowerCase().match(/qq/), weibo = r.toLowerCase().match(/weibo/), xisue = r.toLowerCase().match(/com\.xisue\.zhoumo/), wechat || qq) "activity" == t.type ? location.href = n.h5Activity + t.match : "tag" == t.type ? location.href = n.h5search + t.match : location.href = n.wechat;
			else if (isIPhone || isIPad) {
				"activity" == t.type || "canjia" == t.type ? o.src = n.android + "act?id=" + t.match : "tag" == t.type ? o.src = n.android + "actlist/?sort=distance&tag=" + t.match : o.src = n.ios;
				var i = Date.now();
				setTimeout(function () {
					Date.now() - i < 400 && ("activity" == t.type ? location.href = n.h5Activity + t.match : "tag" == t.type ? location.href = n.h5search + t.match : location.href = n.webIos)
				}, 300)
			} else if (isAndroid) {
				isChrome ? "activity" == t.type || "canjia" == t.type ? location.href = "intent://act?id=" + t.match + "#Intent;scheme=xishzmq;package=com.xisue.zhoumo;end" : "tag" == t.type ? location.href = "intent://actlist/?sort=distance&tag=" + t.match + "#Intent;scheme=xishzmq;package=com.xisue.zhoumo;end" : location.href = "intent://#Intent;scheme=xishzmq;package=com.xisue.zhoumo;end" : "activity" == t.type || "canjia" == t.type ? o.src = n.android + "act?id=" + t.match : "tag" == t.type ? o.src = n.android + "actlist/?sort=distance&tag=" + t.match : o.src = n.android;
				var i = Date.now();
				setTimeout(function () {
					Date.now() - i < 400 && ("activity" == t.type ? location.href = n.h5Activity + t.match : "tag" == t.type ? location.href = n.h5search + t.match : location.href = n.webAndroid)
				}, 300)
			} else "activity" == t.type ? location.href = n.h5Activity + t.match : "tag" == t.type ? location.href = n.h5search + t.match : location.href = n.web
		},
		i = function (e) {
			t(), r(e)
		};
	e.openApp = i
}("undefined" != typeof window ? window : this),
function (e) {
	e.trackEvent = function (e) {
		if ("undefined" != typeof _hmt) try {
			_hmt.push(e)
		} catch (t) {
			console.log(t)
		}
	}
}("undefined" != typeof window ? window : this),
function (e, t) {
	e.zwBack = function (e) {
		e = e || 50, setTimeout(function () {
			var e, n = t.parseQuery(),
				o = "/?";
			1 == history.length ? (e = Object.keys(n), e.forEach(function (e) {
				t.stickyParams.indexOf(e) > -1 && (o += e + "=" + n[e] + "&")
			}), location.href = o) : history.back()
		}, e)
	}
}("undefined" != typeof window ? window : this, utils),
function (e) {
	function t(e) {
		e += "";
		var t, o, i = e.substr(0, 4),
			a = e.substr(4),
			s = r(i, a),
			c = n(i, a),
			u = [],
			d = {};
		for (t = 0; c > t; t++) u.push("0");
		for (t = 0; s > t; t++) o = t + 1, u.push("" + o);
		var p = u.length % 7 === 0 ? 0 : 7 - u.length % 7;
		for (t = 0; p > t; t++) u.push("0");
		return d[e] = u, d
	}

	function n(e, t) {
		var n, o;
		return o = new Date, o.setDate(1), o.setMonth(t - 1), o.setFullYear(e), n = o.getDay()
	}

	function o(e) {
		return e % 100 === 0 ? e % 400 === 0 ? !0 : !1 : e % 4 === 0 ? !0 : !1
	}

	function r(e, t) {
		var n;
		switch (t) {
			case 2:
			case "2":
				n = o(e) ? 29 : 28;
				break;
			default:
				n = 8 > t ? t % 2 === 0 ? 30 : 31 : t % 2 === 0 ? 31 : 30
		}
		return n
	}
	e.zwCalender = {
		getMonthDays: t,
		getOffset: n,
		getMonthDayCount: r,
		isLeapYear: o
	}
}("undefined" != typeof window ? window : this);