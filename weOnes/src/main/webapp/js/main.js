seajs.config({
    alias: {
        "jquery": "jquery-1.11.0.min.js",
        'dialog': '/assets/store/js/artdialog/dialog.js',
        'Jcrop': '/assets/store/js/jquery.Jcrop.min.js',
        'validate':'/assets/store/js/jquery.validate.min.js',
        'tooltip':'/assets/manager/gebo/lib/qtip2/jquery.qtip.min.js',
        'migrate':'/assets/manager/gebo/js/jquery-migrate.min.js',
        'plupload':'/assets/store/js/plupload/plupload.full.min.js',
        'jquery-ui':'/assets/store/js/jquery-ui.min.js',
        'timepicker':'/assets/store/js/jquery-ui-timepicker-addon.min.js',
        'datepick':'/assets/system/My97Datepicker/WdatePicker.js'
    }
});
seajs.use('jquery', function($) {
    $.nano = function(template, data) {
        return template.replace(/\{([\w\.]*)\}/g, function (str, key) {
            var keys = key.split("."), value = data[keys.shift()];
            $.each(keys, function () { value = value[this]; });

            return (value === null || value === undefined) ? "" : value;
        });
    };

    $.fn.J_select = function(){
        return this.each(function(){
            var select = $(this),
                render = function(){
                    var selVal = select.val(),
                        options = select.find("option"),
                        disabled = (select.is(":disabled") || select.attr("readonly")) ? " disabled" : "",
                        textCurrent = '',
                        isRendered = !!select.combo,
                        comboInner = "<span class='txtWrap' style='width:"+(select.outerWidth()-12)+"px'><i class='icoArrow'></i><span class='txt'></span></span>";

                    // select.combo && select.combo.remove();

                    select.combo = isRendered ? select.combo.html(comboInner) : $("<div class='J_combo"+disabled+"'>"+comboInner+"</div>");
                    select.txtWrap = select.combo.find('.txtWrap');
                    select.txt = select.combo.find('.txt');
                    select.comboList = '<div class="listBox"><i class="icoArrow"></i><ul>';

                    options.each(function(i, n){
                        var option = $(n),
                            text = option.text(),
                            val = option.val(),
                            cur = "";

                        if(val == selVal){
                            textCurrent = text;
                            cur = " class='current'";
                        }

                        select.comboList = select.comboList + '<li'+cur+'><i></i><a href="javascript:;" data-val="'+val+'">'+text+'</a></li>';
                    })
                    select.comboList = select.comboList + '</ul></div>';

                    select.txt.text(textCurrent);

                    select.comboList = $(select.comboList).appendTo(select.combo);

                    !isRendered && select.after(select.combo).hide();

                    if(disabled !== "") return;

                    select.txtWrap.on("click", function(){
                        select.trigger("click");
                        select.combo.addClass('active');
                    })

                    select.comboList.on('click', 'li', function(){
                        var li = $(this),
                            val = li.find("a").data("val");

                        textCurrent = li.find("a").text();

                        select.val(val).trigger('change').trigger('click');
                        select.txt.text(textCurrent);
                        li.siblings().removeClass('current').end().addClass('current');
                        select.combo.removeClass('active');
                    })
                };

            render();

            select.on("render", render);
            select.on("selectChange", function(){
                var val = select.val(),
                    a = select.comboList.find("a[data-val='"+val+"']"),
                    li = a.parent();

                textCurrent = a.text();

                select.txt.text(textCurrent);
                li.siblings().removeClass('current').end().addClass('current');
            });

            $(document).on('click', function(e){
                if ($(e.target).closest(select.combo).length < 1) {
                    select.combo.removeClass('active');
                }
            })
        })
    };

    $.fn.radioCheckbox = function(){
        return this.each(function(){
            var that = $(this),
                inputs = that.find("input[type='radio'],input[type='checkbox']");

            inputs.each(function(){
                var input = $(this),
                    checked = input.is(":checked"),
                    li = input.parent(),
                    label = li.find("label"),
                    ico = $("<i class='ico icoInput'></i>");

                ico.appendTo(li);
                input.hide();

                if(checked) li.addClass('checked');

                label.add(ico).click(function(){
                    input.trigger('click');
                    inputs.trigger('change');
                })

                input.click(function(event) {
                    if(input.attr("readonly")) return false;
                });

                input.change(function(){
                    li.toggleClass('checked', input.is(":checked"));
                }).trigger('change');
            });
        })
    }

    $(function(){
        $(".J_select").J_select();
        $(".J_radio, .J_checkbox").radioCheckbox();
    })
})
