
//自 序列化表单
function myserialize(formId){
    var form=document.getElementById(formId);
    var arr={};
    for (var i = 0; i < form.elements.length; i++) {
        var feled=form.elements[i];
        switch(feled.type) {
            case undefined:
            case 'button':
            case 'file':
            case 'reset':
            case 'submit':
                break;
            case 'checkbox':
            case 'radio':
                if (!feled.checked) {
                    break;
                }
            default:
                if (arr[feled.name]) {
                    arr[feled.name]=arr[feled.name]+','+feled.value;
                }else{
                    arr[feled.name]=feled.value;

                }
        }
    }
    return arr
}


/**
 * 设置表单值
 * @param form          Form表单id或表单jquery DOM对象
 * @param data          json对象，多选时为数组
 * 代码实现参考此开源项目https://github.com/kflorence/jquery-deserialize/
 */
var deserialize = function(form,data){
    var rcheck = /^(?:radio|checkbox)$/i,
        rselect = /^(?:option|select-one|select-multiple)$/i,
        rvalue = /^(?:button|color|date|datetime|datetime-local|email|hidden|month|number|password|range|reset|search|submit|tel|text|textarea|time|url|week)$/i;

    var $form = (typeof(form)=="string" ? $("#"+form) : form);

    //得到所有表单元素
    function getElements( elements ) {
        //此处elements为jquery对象。这个map函数使用来便利elements数组的.如存在多个form表单，则便利多个form表单
        return elements.map(function(index, domElemen) {
            //this代表form表单，this.elements获取表单中的DOM数组. jQuery.makeArray 转换一个类似数组的对象成为真正的JavaScript数组。
            return this.elements ? jQuery.makeArray( this.elements ) : this;
            //过滤不启用的选项
        }).filter( ":input:not(:disabled)" ).get();
    }
    //把表单元素转为json对象
    function elementsToJson( elements ) {
        var current,elementsByName = {};
        //elementsByName对象为{控件名：控件元素或控件元素数组}
        jQuery.each( elements, function( i, element ) {
            current = elementsByName[ element.name ];
            elementsByName[ element.name ] = current === undefined ? element :
                //如果已经是一个数组了，那么就添加，否则构造一个数组
                ( jQuery.isArray( current ) ? current.concat( element ) : [ current, element ] );
        });
        return elementsByName;
    }

    var elementsJson = elementsToJson(getElements($form));
    //data是一个对象
    for(var key in data){
        var val = data[key];
        var dataArr = [];//更具数据直接构造一个jQUery序列化后的数组形式。
        //判断值是否为数组
        if( $.isArray(val)){
            for(var i= 0,v;v=val[i++];){
                //是数组那么就变成多个对象形式
                dataArr.push({name:key,value:v});
            }
        } else{
            //不是数组直接构造
            dataArr.push({name:key,value:val});
        }

        //根据数据构造的这个数组进行操作
        for(var m= 0,vObj;vObj=dataArr[m++];){
            var element;
            //如果表单中无元素则跳过
            if ( !( element = elementsJson[vObj.name] ) ) {
                continue;
            }
            //判断元素是否为数组,暂时获取第一个元素，后面会有迭代赋值。
            var type = element.length?element[0]:element;
            //元素类型
            type = ( type.type || type.nodeName ).toLowerCase();

            var property = null;
            //是单值类型
            if ( rvalue.test( type ) ) {
                element.value = (typeof(vObj.value)=="undefined" || vObj.value==null)?"":vObj.value;
                //checkbox
            } else if ( rcheck.test( type ) ) {
                property = "checked";
                //select
            } else if ( rselect.test( type ) ) {
                property = "selected";
            }
            //判断类型是否为多选
            if(property) {
                //如果是，则迭代多选的元素赋值
                for(var n= 0,e;e=element[n++];){
                    if(e.value==vObj.value){
                        //设置选中
                        e[property] = true;
                    }
                }
            }
        }
    }
};