! function(t) {
    "use strict";
    "function" == typeof define && define.amd ? define(["jquery"], t) : t("object" == typeof exports && "function" == typeof require ? require("jquery") : jQuery)
}(function(t, e) {
    function s(e, s) {
        this._name = i, this.el = e, this.$el = t(e), this.$el.prop("multiple") || (this.settings = t.extend({}, o, s, this.$el.data()), this._defaults = o, this.$options = this.$el.find("option, optgroup"), this.init(), t.fn[i].instances.push(this))
    }
    var i = "comboSelect",
        n = "comboselect",
        o = {
            comboClass: "combo-select",
            comboArrowClass: "combo-arrow",
            comboDropDownClass: "combo-dropdown",
            inputClass: "combo-input text-input",
            disabledClass: "option-disabled",
            hoverClass: "option-hover",
            selectedClass: "option-selected",
            markerClass: "combo-marker",
            themeClass: "",
            maxHeight: 200,
            extendStyle: !0,
            focusInput: !0
        },
        r = {
            ESC: 27,
            TAB: 9,
            RETURN: 13,
            LEFT: 37,
            UP: 38,
            RIGHT: 39,
            DOWN: 40,
            ENTER: 13,
            SHIFT: 16
        },
        l = /android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(navigator.userAgent.toLowerCase());
    t.extend(s.prototype, {
        init: function() {
            this._construct(), this._events()
        },
        _construct: function() {
            var e = this;
            this.$el.data("plugin_" + n + "_tabindex", this.$el.prop("tabindex")), !l && this.$el.prop("tabIndex", -1), this.$container = this.$el.wrapAll('<div class="' + this.settings.comboClass + " " + this.settings.themeClass + '" />').parent(), this.settings.extendStyle && this.$el.attr("style") && this.$container.attr("style", this.$el.attr("style")), this.$arrow = t('<div class="' + this.settings.comboArrowClass + '" />').appendTo(this.$container), this.$dropdown = t('<ul class="' + this.settings.comboDropDownClass + '" />').appendTo(this.$container);
            var s = "",
                i = 0,
                o = "";
            this.selectedIndex = this.$el.prop("selectedIndex"), this.$options.each(function(t, n) {
                return "optgroup" == n.nodeName.toLowerCase() ? s += '<li class="option-group">' + this.label + "</li>" : (n.value || (o = n.innerHTML), s += '<li class="' + (this.disabled ? e.settings.disabledClass : "option-item") + " " + (i == e.selectedIndex ? e.settings.selectedClass : "") + '" data-index="' + i + '" data-value="' + this.value + '">' + this.innerHTML + "</li>", void i++)
            }), this.$dropdown.html(s), this.$items = this.$dropdown.children(), this.$input = t('<input type="text"' + (l ? 'tabindex="-1"' : "") + ' placeholder="' + o + '" class="' + this.settings.inputClass + '">').appendTo(this.$container), this._updateInput()
        },
        _events: function() {
            this.$container.on("focus.input", "input", t.proxy(this._focus, this)), this.$container.on("mouseup.input", "input", function(t) {
                t.preventDefault()
            }), this.$container.on("blur.input", "input", t.proxy(this._blur, this)), this.$el.on("change.select", t.proxy(this._change, this)), this.$el.on("focus.select", t.proxy(this._focus, this)), this.$el.on("blur.select", t.proxy(this._blurSelect, this)), this.$container.on("click.arrow", "." + this.settings.comboArrowClass, t.proxy(this._toggle, this)), this.$container.on("comboselect:close", t.proxy(this._close, this)), this.$container.on("comboselect:open", t.proxy(this._open, this)), t("html").off("click.comboselect").on("click.comboselect", function() {
                t.each(t.fn[i].instances, function(t, e) {
                    e.$container.trigger("comboselect:close")
                })
            }), this.$container.on("click.comboselect", function(t) {
                t.stopPropagation()
            }), this.$container.on("keydown", "input", t.proxy(this._keydown, this)), this.$container.on("keyup", "input", t.proxy(this._keyup, this)), this.$container.on("click.item", ".option-item", t.proxy(this._select, this))
        },
        _keydown: function(t) {
            switch (t.which) {
                case r.UP:
                    this._move("up", t);
                    break;
                case r.DOWN:
                    this._move("down", t);
                    break;
                case r.TAB:
                    this._enter(t);
                    break;
                case r.RIGHT:
                    this._autofill(t);
                    break;
                case r.ENTER:
                    this._enter(t)
            }
        },
        _keyup: function(t) {
            switch (t.which) {
                case r.ESC:
                    this.$container.trigger("comboselect:close");
                    break;
                case r.ENTER:
                case r.UP:
                case r.DOWN:
                case r.LEFT:
                case r.RIGHT:
                case r.TAB:
                case r.SHIFT:
                    break;
                default:
                    this._filter(t.target.value)
            }
        },
        _enter: function(t) {
            var e = this._getHovered();
            if (e.length && this._select(e), t && t.which == r.ENTER) {
                if (!e.length) return this._blur(), !0;
                t.preventDefault()
            }
        },
        _move: function(t) {
            var e = this._getVisible(),
                s = this._getHovered(),
                i = s.prevAll(".option-item").filter(":visible").length,
                n = e.length;
            switch (t) {
                case "up":
                    i--, i < 0 && (i = n - 1);
                    break;
                case "down":
                    i++, i >= n && (i = 0)
            }
            e.removeClass(this.settings.hoverClass).eq(i).addClass(this.settings.hoverClass), this.opened || this.$container.trigger("comboselect:open"), this._fixScroll()
        },
        _select: function(e) {
            var s = t(e.currentTarget ? e.currentTarget : e);
            if (s.length) {
                var i = s.data("index");
                this._selectByIndex(i), this.$container.trigger("comboselect:close")
            }
        },
        _selectByIndex: function(t) {
            "undefined" == typeof t && (t = 0), this.$el.prop("selectedIndex") != t && this.$el.prop("selectedIndex", t).trigger("change")
        },
        _autofill: function() {
            var t = this._getHovered();
            if (t.length) {
                var e = t.data("index");
                this._selectByIndex(e)
            }
        },
        _filter: function(e) {
            var s = this,
                i = this._getAll();
            needle = t.trim(e).toLowerCase(), reEscape = new RegExp("(\\" + ["/", ".", "*", "+", "?", "|", "(", ")", "[", "]", "{", "}", "\\"].join("|\\") + ")", "g"), pattern = "(" + e.replace(reEscape, "\\$1") + ")", t("." + s.settings.markerClass, i).contents().unwrap(), needle ? (this.$items.filter(".option-group, .option-disabled").hide(), i.hide().filter(function() {
                var e = t(this),
                    i = t.trim(e.text()).toLowerCase();
                if (i.toString().indexOf(needle) != -1) return e.html(function(t, e) {
                    return e.replace(new RegExp(pattern, "gi"), '<span class="' + s.settings.markerClass + '">$1</span>')
                }), !0
            }).show()) : this.$items.show(), this.$container.trigger("comboselect:open")
        },
        _highlight: function() {
            var t = this._getVisible().removeClass(this.settings.hoverClass),
                e = t.filter("." + this.settings.selectedClass);
            e.length ? e.addClass(this.settings.hoverClass) : t.removeClass(this.settings.hoverClass).first().addClass(this.settings.hoverClass)
        },
        _updateInput: function() {
            var e = this.$el.prop("selectedIndex");
            return this.$el.val() ? (text = this.$el.find("option").eq(e).text(), this.$input.val(text)) : this.$input.val(""), this._getAll().removeClass(this.settings.selectedClass).filter(function() {
                return t(this).data("index") == e
            }).addClass(this.settings.selectedClass)
        },
        _blurSelect: function() {
            this.$container.removeClass("combo-focus")
        },
        _focus: function(t) {
            this.$container.toggleClass("combo-focus", !this.opened), l || (this.opened || this.$container.trigger("comboselect:open"), this.settings.focusInput && t && t.currentTarget && "INPUT" == t.currentTarget.nodeName && t.currentTarget.select())
        },
        _blur: function() {
            var e = t.trim(this.$input.val().toLowerCase()),
                s = !isNaN(e),
                i = this.$options.filter(function() {
                    return s ? parseInt(t.trim(this.innerHTML).toLowerCase()) == e : t.trim(this.innerHTML).toLowerCase() == e
                }).prop("index");
            this._selectByIndex(i)
        },
        _change: function() {
            this._updateInput()
        },
        _getAll: function() {
            return this.$items.filter(".option-item")
        },
        _getVisible: function() {
            return this.$items.filter(".option-item").filter(":visible")
        },
        _getHovered: function() {
            return this._getVisible().filter("." + this.settings.hoverClass)
        },
        _open: function() {
            var e = this;
            this.$container.addClass("combo-open"), this.opened = !0, this.settings.focusInput && setTimeout(function() {
                !e.$input.is(":focus") && e.$input.focus()
            }), this._highlight(), this._fixScroll(), t.each(t.fn[i].instances, function(t, s) {
                s != e && s.opened && s.$container.trigger("comboselect:close")
            })
        },
        _toggle: function() {
            this.opened ? this._close.call(this) : this._open.call(this)
        },
        _close: function() {
            this.$container.removeClass("combo-open combo-focus"), this.$container.trigger("comboselect:closed"), this.opened = !1, this.$items.show()
        },
        _fixScroll: function() {
            if (!this.$dropdown.is(":hidden")) {
                var t = this._getHovered();
                if (t.length) {
                    var e, s, i, n = t.outerHeight();
                    e = t[0].offsetTop, s = this.$dropdown.scrollTop(), i = s + this.settings.maxHeight - n, e < s ? this.$dropdown.scrollTop(e) : e > i && this.$dropdown.scrollTop(e - this.settings.maxHeight + n)
                }
            }
        },
        dispose: function() {
            this.$arrow.remove(), this.$input.remove(), this.$dropdown.remove(), this.$el.removeAttr("tabindex"), this.$el.data("plugin_" + n + "_tabindex") && this.$el.prop("tabindex", this.$el.data("plugin_" + n + "_tabindex")), this.$el.unwrap(), this.$el.removeData("plugin_" + n), this.$el.removeData("plugin_" + n + "_tabindex"), this.$el.off("change.select focus.select blur.select")
        }
    }), t.fn[i] = function(e, i) {
        return this.each(function() {
            var o = t(this),
                r = o.data("plugin_" + n);
            "string" == typeof e ? r && "function" == typeof r[e] && r[e](i) : (r && r.dispose && r.dispose(), t.data(this, "plugin_" + n, new s(this, e)))
        }), this
    }, t.fn[i].instances = []
});
