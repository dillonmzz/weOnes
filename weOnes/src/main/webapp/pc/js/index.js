(function () {
    "use strict";
    define("lib/cookie", {
        get: function (e) {
            if (document.cookie.length > 0) {
                var t, n = document.cookie.indexOf(e + "=");
                if (n != -1)return n = n + e.length + 1, t = document.cookie.indexOf(";", n), t == -1 && (t = document.cookie.length), unescape(document.cookie.substring(n, t))
            }
            return ""
        }, set: function (e, t, n, r) {
            return document.cookie = e + "=" + escape(t) + (n == null ? "" : ";expires=" + n.toGMTString()) + ";path=" + (r || "/"), this
        }, remove: function (e, t) {
            var n = new Date;
            n.setTime(n.getTime() - 1);
            var r = this.get(e);
            r != null && (document.cookie = e + "=" + r + ";expires=" + n.toGMTString() + ";path=" + (t || "/"))
        }
    }), define("lib/slide", [], function () {
        var e = function (e, t) {
            this.setings = $.extend({
                autoPlay: !0,
                speed: 300,
                timer: 5e3,
                buttons: !0,
                hover: !0
            }, t), this._slides = {}, this.support = {
                touch: "ontouchstart"in window, transitions: function (e) {
                    var t = ["transformProperty", "WebkitTransform", "MozTransform", "OTransform"];
                    for (var n in t)if (typeof e.style[t[n]] != "undefined")return !0;
                    return !1
                }(document.createElement("div"))
            };
            if (!e.length)return;
            this.$slider = e, this.$slides = this.$slider.children(".slides"), this.$prev = this.$slider.children(".prev"), this.$next = this.$slider.children(".next"), this.$slides.length === 2 && (this.$slider.prepend(this.$slides.clone()), this.$slides = this.$slider.children(".slides"));
            if (!this.$slides.length)return;
            this.length = this.$slides.length, this.index = parseInt(this.setings.index, 10) || 0, this.width = this.$slides.eq(0).width(), this.timer = null;
            var n = this;
            this.handleEvents = function (e) {
                switch (e.type) {
                    case"touchstart":
                        n._start(e);
                        break;
                    case"touchmove":
                        n._move(e);
                        break;
                    case"touchend":
                        n._end(e)
                }
                e.stopPropagation()
            }, this.length > 2 ? this.setup() : (this.$prev.remove(), this.$next.remove()), $(window).resize(function () {
                n._resize()
            })
        };
        return e.prototype.setup = function () {
            var e = this, t, n;
            this.$slides.each(function (t, n) {
                n.style.zIndex = e.length - t
            }), t = (this.length + this.index - 1) % this.length, n = (this.length + this.index + 1) % this.length, this._go(this.index, 0, 0), this._go(t, -this.width, 0), this._go(n, this.width, 0), this.setings.buttons && (this.$next.on("click", function () {
                e._click((e.length + e.index + 1) % e.length, "next")
            }), this.$prev.on("click", function () {
                e._click((e.length + e.index - 1) % e.length, "prev")
            })), this.setings.autoPlay && this.autoPlay(), this.support.touch && this.$slider.on("touchstart", function (t) {
                e._start(t)
            }), this.setings.hover && this.$slider.hover(function () {
                clearTimeout(e.timer)
            }, function () {
                e.timer = setTimeout(function () {
                    e._run((e.length + e.index + 1) % e.length)
                }, e.setings.timer)
            })
        }, e.prototype._resize = function () {
            var e = this, t, n;
            this.width = this.$slides.eq(0).width(), t = (this.length + this.index - 1) % this.length, n = (this.length + this.index + 1) % this.length, this._go(this.index, 0, 0), this._go(t, -this.width, 0), this._go(n, this.width, 0)
        }, e.prototype._go = function (e, t, n) {
            var r = this;
            this.support.transitions ? r._translate(e, t, n) : r._left(e, t, n)
        }, e.prototype._left = function (e, t, n) {
            var r = this.$slides.eq(e), i = this;
            n === 0 ? r.css({left: t + "px"}) : r.animate({left: t + "px"}, {duration: n}), r.css({zIndex: this.length})
        }, e.prototype._translate = function (e, t, n) {
            var r = this.$slides.get(e), i = r.style;
            i && (i.webkitTransitionDuration = i.MozTransitionDuration = i.OTransitionDuration = i.transitionDuration = n + "ms", i.webkitTransform = "translate(" + t + "px,0)translateZ(0)", i.MozTransform = i.OTransform = "translateX(" + t + "px)", i.zIndex = this.length)
        }, e.prototype.autoPlay = function () {
            var e = this;
            this.timer = setTimeout(function () {
                e._run((e.length + e.index + 1) % e.length)
            }, this.setings.timer)
        }, e.prototype._run = function (e, t) {
            this.index = e;
            var n = (this.length + this.index - 1) % this.length, r = (this.length + this.index + 1) % this.length, i = this, t = t || "next";
            t === "next" ? (i._go(r, this.width, 0), i._go(n, -this.width, this.setings.speed), i._go(this.index, 0, this.setings.speed)) : (i._go(n, -this.width, 0), i._go(this.index, 0, this.setings.speed), i._go(r, this.width, this.setings.speed)), this.setings.autoPlay && this.autoPlay()
        }, e.prototype._click = function (e, t) {
            this.index = e;
            var n = (this.length + this.index - 1) % this.length, r = (this.length + this.index + 1) % this.length, i = this, t = t || "next";
            t === "next" ? (i._go(r, this.width, 0), i._go(n, -this.width, this.setings.speed), i._go(this.index, 0, this.setings.speed)) : (i._go(n, -this.width, 0), i._go(r, this.width, this.setings.speed), i._go(this.index, 0, this.setings.speed))
        }, e.prototype._start = function (e) {
            var t = e.originalEvent.touches[0];
            this._slides.start = {
                x: t.pageX,
                y: t.pageY,
                time: +(new Date)
            }, self = this, this._slides.isScrolling = !1, this._slides.delta = {}, this.$slider.on("touchmove", this.handleEvents), $(document).on("touchend", this.handleEvents)
        }, e.prototype._move = function (e) {
            if (!(e.originalEvent.touches.length > 1 || e.originalEvent.scale && !e.originalEvent.scale)) {
                var t = e.originalEvent.touches[0];
                this._slides.delta = {
                    x: t.pageX - this._slides.start.x,
                    y: t.pageY - this._slides.start.y
                }, typeof this._slides.isScrolling != "undefined" && (this._slides.isScrolling = !!(this._slides.isScrolling || Math.abs(this._slides.delta.x) < Math.abs(this._slides.delta.y)));
                if (!this._slides.isScrolling) {
                    e.preventDefault(), this.timer && clearTimeout(this.timer);
                    var n = (this.length + this.index - 1) % this.length, r = (this.length + this.index + 1) % this.length, i = this;
                    this._go(this.index, this._slides.delta.x, 0), this._slides.delta.x > 0 ? this._go(n, -this.width + this._slides.delta.x, 0) : this._go(r, this._slides.delta.x + this.width, 0)
                }
            }
        }, e.prototype._end = function (e) {
            var t = this;
            if (!this._slides.isScrolling) {
                var n = +(new Date) - this._slides.start.time;
                if (n < 250 && Math.abs(this._slides.delta.x) > 20 || Math.abs(this._slides.delta.x) > this.width / 2) {
                    var r = this._slides.delta.x < 0, i = (this.length + this.index - 1) % this.length, s = (this.length + this.index + 1) % this.length;
                    r ? t._run(s, "next") : t._run(i, "prev")
                } else {
                    var i = (this.length + this.index - 1) % this.length, s = (this.length + this.index + 1) % this.length;
                    t._go(s, this.width, t.setings.speed), t._go(i, -this.width, t.setings.speed), t._go(t.index, 0, t.setings.speed), !t.timer && t.setings.autoPlay && t.autoPlay()
                }
            }
            this.$slider.off("touchmove", this.handleEvents), $(document).off("touchend", this.handleEvents)
        }, e
    }), require(["lib/cookie", "lib/slide"], function (e, t) {
        var n = "ontouchstart"in window, r = n ? "touchstart touchmove" : "mouseenter", i = n ? "touchend" : "mouseleave";
        $(window).width() > 767 && ($(".aTutor").on(r, function (e) {
            $(this).addClass("hover")
        }), $(".aTutor").on(i, function (e) {
            $(this).removeClass("hover")
        }));
        var s = $("#meetGuide"), o = s.height();
        $("#guideBtn").click(function (e) {
            e.preventDefault(), $(".container").addClass("pusher-down"), $("#meetGuide").addClass("pull-down"), $("#header").addClass("header-hide"), $(window).scrollTop(0)
        }), $("#meetGuide a[data-action=close-guide]").click(function (e) {
            e.preventDefault(), $(".container").removeClass("pusher-down"), $("#meetGuide").removeClass("pull-down"), $("#header").removeClass("header-hide")
        }), $(window).scroll(function (e) {
            var t = $(this), n = $(".container"), r = $("#meetGuide");
            r.hasClass("pull-down") && t.scrollTop() >= 550 && (t.scrollTop(0), r.css({
                "-webkit-transition": "all 0",
                transition: "all 0"
            }), n.css({
                "-webkit-transition": "all 0",
                transition: "all 0"
            }), n.removeClass("pusher-down"), r.removeClass("pull-down"), $("#header").removeClass("header-hide"), setTimeout(function () {
                r.css({
                    "-webkit-transition": "all 0.5s",
                    transition: "all 0.5s"
                }), n.css({"-webkit-transition": "all 0.5s", transition: "all 0.5s"})
            }, 500))
        }), $(document).delegate("*[data-action=selectCity]", "click", function (t) {
            t.preventDefault();
            var n = $(this), r = n.attr("href"), i = n.attr("data-city"), s = new Date, o = new Date, u = 31536e6;
            o.setTime(s.getTime() + u), e.set("city", i, o), location.href = r
        }), $(document).click(function (e) {
            var t = $("a[data-action=openCityList]"), n = $(".dropSelectDefault"), r = n.parent(), i = "drop-select-hide";
            if ($.contains(r[0], e.target)) {
                if (r.hasClass(i))return r.removeClass(i), n.attr("data-action", "closeCityList"), !1
            } else r.addClass(i), n.attr("data-action", "openCityList")
        }), $(document).delegate("*[data-action=closeCityList]", "click", function (e) {
            var t = $(this), n = "drop-select-hide", r = t.parents(".drop-select"), i = $(".dropSelectDefault");
            if (r.length)return r.addClass(n), i.attr("data-action", "openCityList"), !1
        }),
            $(".indexTopicInfo span").each(function () {
            var e = 61;
            $(this).text().length > e && ($(this).text($(this).text().substring(0, e)), $(this).html($(this).html() + "…"))
        }), new t($("#slider"), {}), window.zhuge && $(document).delegate("form.search", "submit", function (t) {
            var n = $(this).find("input").val().trim();
            if (!n) {
                t.preventDefault();
                return
            }
            zhuge.track("搜索", {from: "home", keyword: n, page: "首页", city: e.get("city") || "beijing"})
        })
    }), define("index", function () {
    })
})();