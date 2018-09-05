<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
    //将时间的毫秒数转换为标准时间
    function formatTime(val) {
        var date=new Date(val);
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        month=month>9?month:('0'+month);
        var day=date.getDate();
        day=day>9?day:('0'+day);
        var time=year+'/'+month+'/'+day;
        return time;
    }
    $(function(){
        $("#btn_user").click(function (data) {
            var titles = $("#cc_export").combotree("getText");
            var params = $("#cc_export").combotree("getValues");
            var selectedParam = "";
            $.each(params, function (index, param) {
                if (params.length - 1 == index) {
                    selectedParam += param;
                } else {
                    selectedParam += param + ",";
                }
            })
            $("#customer_form").form("submit", {
                url: "${pageContext.request.contextPath}/user/export",
                updateUrl: "${pageContext.request.contextPath}/user/update",
                queryParams: {
                    titles: titles,
                    params: selectedParam
                },
            })
            $("#export").dialog('close');//关闭对话框
        })
        $('#dg_user').edatagrid({
            url:'${ pageContext.request.contextPath }/user/queryAll',
            updateUrl: "${pageContext.request.contextPath}/user/update",
            fit:true,
            columns:[[
                {field: 'name',title:'姓名',width:100},
                {field: 'dharamName',title:'法名',width:100},
                {field: 'sex',title:'性别',width:100,},
                {field: 'province',title:'省份',width:100,},
                {field: 'city',title:'城市',width:100,},
                {field: 'sign',title:'个性签名',width:300,
                    editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field:'phoneNum',title:'手机号',width:100,
                    editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field:'status',title:'状态',width:100,
                    editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field:'regisDate',title:'注册日期',width:100,
                    formatter:function(value,row,index){
                        return  formatTime(value);
                    }
                },
            ]],

            toolbar: [{
                iconCls: 'icon-redo',
                text:"全部导出",
                handler: function(){
                    location.href="${ pageContext.request.contextPath }/user/exportAll";
                }
            },'-',{
                iconCls: 'icon-back',
                text:"导入",
                handler: function(){
                    location.href="";
                }
            },'-',{
                iconCls: 'icon-redo',
                text:"自定义导出",
                handler: function(){
                    $('#export').dialog({
                        title: '导出',
                        width: 450,
                        height: 400,
                        collapsible: true,
                        minimizable: true,
                        maximizable: true,
                        modal: true,//遮罩效果
                    });
                    $('#cc_export').combotree({
                        url: '',
                        checkbox:true,
                        multiple:true,
                        onlyLeafCheck:true,
                        required: true
                    });
                }
            },'-',{
                iconCls: 'icon-edit',
                text:"修改",
                handler: function(){
                    //获取当前选中的行
                    var row = $("#dg_user").edatagrid('getSelected');
                    if(row==null){
                        $.messager.alert("提示", "请选择要修改的数据！");
                    }else {
                        var index = $("#dg_user").edatagrid("getRowIndex", row);
                        row.createDate=formatTime(row.createDate);
                        //当前行可编辑
                        $("#dg_user").edatagrid("editRow", index);
                    }
                },
            },'-',{
                iconCls: 'icon-save',
                text:"保存",
                handler: function(){

                    $("#dg_user").edatagrid("saveRow");
                }
            }], view: detailview,
            detailFormatter: function(rowIndex, rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/upload/' + rowData.photoImg + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>上师: ' + rowData.guru.name + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
        });
    });
</script>
<table id="dg_user"></table>
<div id="export" style="text-align: center; padding-top: 35px">
    <input id="cc_export" data-options="
    data:[{
            id: 'custome',
            text: '请选择',
            'checked':false,
            children: [{
                'id':'id',
                'text': '编号',
                'checked': true
            },{
                'id':'name',
                'text': '姓名',
                'checked': true
            },{
                'id':'photoImg',
                'text': '头像',
                'checked': true
            },{
                'id':'dharamName',
                'text': '法名',
                'checked': true
            },{
                'id':'sex',
                'text': '性别',
                'checked': true
            },{
                'id':'province',
                'text': '省份',
                'checked': true
            },{
                'id':'city',
                'text': '城市',
                'checked': true
            },{
                'id':'sign',
                'text': '签名',
                'checked': true
            },{
                'id':'phoneNum',
                'text': '手机号',
                'checked': true
            },{
                'id':'status',
                'text': '状态',
                'checked': true
            },{
                'id':'regisDate',
                'text': '注册日期',
                'checked': true
            }]
        }]
"/>
<form action="" method="post" id="customer_form">
    <a id="btn_user" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
</form>
</div>
</html>