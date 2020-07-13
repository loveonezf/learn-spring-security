(function ($) {
    var learn = window.learn = window.learn || {};

    var Util = function () {
    };

    Util.prototype.request = function request(url, data, successHandler, errorHandler, opts) {
        var self = this,
            reqOpts = {
                timeout: 600000,
                global: true
            }, json = data ? self.stringify(data) : undefined;

        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            global: reqOpts.global,
            timeout: reqOpts.timeout,
            processData: false,
            data: json,
            //dataType: 'json',
            contentType: 'application/json',
            success: function (result) {
                try {
                    successHandler && successHandler(result);
                } catch (e) {
                    //throw e;
                    // if (e.message) {
                    //     self.showError(e.message);
                    // } else {
                    //     self.showError(e);
                    // }
                }
            },
            error: function (jqXHR, textStatus, error) {
                try {
                    // if (reqOpts.showLoading) self.closeLoading();

                    if (textStatus == "timeout") {
                        // if (reqOpts.timeoutHandler) {
                        //     jqXHR.isErrorHandled = true;
                        //     reqOpts.timeoutHandler("请求:" + url + "超时");
                        // }
                    } else if (errorHandler) {
                        var exMsg;
                        try {
                            exMsg = self.parse(jqXHR.responseText);
                        } catch (e) {
                        }
                        if (exMsg) {
                            jqXHR.isErrorHandled = true;
                            errorHandler(exMsg.Message);
                        }
                        return;
                    }
                } catch (e) {
                }
            }
        });
    };

    $.extend({
        learn: new Util()
    });
})(jQuery);