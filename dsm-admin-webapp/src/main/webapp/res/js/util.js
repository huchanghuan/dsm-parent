/**
 * html Encode/Decode
 */
function htmlEncode(value) {
    /**
	 * create a in-memory div, set it's inner text(which jQuery automatically
	 * encodes) then grab the encoded contents back out. The div never exists on
	 * the page.
	 */
    return $("<div/>").text(value).html();
}

function htmlDecode(value) {
    return $("<div/>").html(value).text();
}

/**
 * js截取字符串，中英文都能用
 * 
 * @param str：需要截取的字符串
 * @param len：需要截取的长度
 */
function cutstr(str, len) {
    if (!Boolean(str)) return "";
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            // 中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    if (str_length < len) {
        // 如果给定字符串小于指定长度，则返回源字符串；
        return str;
    }
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 例子： (new
 * Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function(fmt) {
    var o = {
        "M+":this.getMonth() + 1,
        // 月份
        "d+":this.getDate(),
        // 日
        "h+":this.getHours(),
        // 小时
        "m+":this.getMinutes(),
        // 分
        "s+":this.getSeconds(),
        // 秒
        "q+":Math.floor((this.getMonth() + 3) / 3),
        // 季度
        S:this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] :("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return fmt;
};

/**
 * 把UTC时间格式化
 * 
 * @param time
 * @param formatStr
 * @returns
 */
function formatUTC(time, formatStr) {
    if (isNaN(time)) return time;
    if (time == 0) return "";
    var date = new Date();
    date.setTime(time);
    return date.format(formatStr);
}

/**
 * 格式化毫秒数成时分秒
 */
function formatTime(time){
	if(time>1000){
		var s = time/1000;
		if(s>=60){
			var m = s/60;
			if(m>=60){
				var h = m/60;
			}
		}
	}
	return h+":"+m+":"+s;
}

/**
 * 从数组中找到匹配项
 * @param list
 * @param properties
 * @returns
 */
function match(list, properties) {
    for (var index in list) {
        var item = list[index];
        for (var key in properties) {
            if (item.hasOwnProperty(key) && item[key] == properties[key]) {
                return item;
            }
        }
    }
}

/**
 * Map封装
 */
function HashMap(){
    this.map = {};
}

HashMap.prototype = {
	put : function(key , value){
		this.map[key] = value;
    },
    get : function(key){
    	if(this.map.hasOwnProperty(key)){
    		return this.map[key];
    	}
    	return null;
    },
    remove : function(key){
    	if(this.map.hasOwnProperty(key)){
    		return delete this.map[key];
    	}
    	return false;
    },
    removeAll : function(){
    	this.map = {};
    },
    keySet : function(){
    	var _keys = [];
    	for(var i in this.map){
    		_keys.push(i);
    	}
    	return _keys;
    }
};
HashMap.prototype.constructor = HashMap;

