! function(n) {
    var e = {
        init: function(n, t) {
            return function() {
                e.fillHtml(n, t), e.bindEvent(n, t)
            }()
        },
        fillHtml: function(n, e) {
            return function() {
                n.empty(), e.current > 1 ? n.append('<a href="javascript:;" class="prevPage">上一页</a>') : (n.remove(".prevPage"), n.append('<span class="disabled">上一页</span>')), 1 != e.current && e.current >= 4 && 4 != e.pageCount && n.append('<a href="javascript:;" class="tcdNumber">1</a>'), e.current - 2 > 2 && e.current <= e.pageCount && e.pageCount > 5 && n.append("<span>...</span>");
                var t = e.current - 2,
                    a = e.current + 2;
                for ((t > 1 && e.current < 4 || 1 == e.current) && a++, e.current > e.pageCount - 4 && e.current >= e.pageCount && t--; t <= a; t++) t <= e.pageCount && t >= 1 && (t != e.current ? n.append('<a href="javascript:;" class="tcdNumber">' + t + "</a>") : n.append('<span class="current">' + t + "</span>"));
                e.current + 2 < e.pageCount - 1 && e.current >= 1 && e.pageCount > 5 && n.append("<span>...</span>"), e.current != e.pageCount && e.current < e.pageCount - 2 && 4 != e.pageCount && n.append('<a href="javascript:;" class="tcdNumber">' + e.pageCount + "</a>"), e.current < e.pageCount ? n.append('<a href="javascript:;" class="nextPage">下一页</a>') : (n.remove(".nextPage"), n.append('<span class="disabled">下一页</span>'))
            }()
        },
        bindEvent: function(t, a) {
            return function() {
                t.on("click", "a.tcdNumber", function() {
                    var r = parseInt(n(this).text());
                    e.fillHtml(t, {
                        current: r,
                        pageCount: a.pageCount
                    }), "function" == typeof a.backFn && a.backFn(r)
                }), t.on("click", "a.prevPage", function() {
                    var n = parseInt(t.children("span.current").text());
                    e.fillHtml(t, {
                        current: n - 1,
                        pageCount: a.pageCount
                    }), "function" == typeof a.backFn && a.backFn(n - 1)
                }), t.on("click", "a.nextPage", function() {
                    var n = parseInt(t.children("span.current").text());
                    e.fillHtml(t, {
                        current: n + 1,
                        pageCount: a.pageCount
                    }), "function" == typeof a.backFn && a.backFn(n + 1)
                })
            }()
        }
    };
    n.fn.createPage = function(t) {
        var a = n.extend({
            pageCount: 10,
            current: 1,
            backFn: function() {}
        }, t);
        e.init(this, a)
    }
}(jQuery);
