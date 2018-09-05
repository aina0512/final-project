<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(function () {
        function timestampToTime(timestamp) {
            var date = new Date(timestamp );//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            var Y = date.getFullYear() + '-';
            var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            var D = date.getDate() + ' ';
            var h = date.getHours() + ':';
            var m = date.getMinutes() + ':';
            var s = date.getSeconds();
            return Y+M+D+h+m+s;
        }
        var toolbar = [{
            iconCls: 'icon-edit',
            text: "添加",
            handler: function () {
                /*
                 * 录入数据
                 * */
                $("#dd").dialog("open");
            },
        }, '-', {
            text: "删除",
            iconCls: 'icon-help',
            handler: function () {
                /*
                 * 删除数据
                 * */
                // 销毁所有选择的行
                $('#dg').edatagrid('destroyRow');
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-help',
            handler: function () {
                /*
                 *使当前选中行可编辑模式
                 * */
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    var index = $("#dg").edatagrid("getRowIndex", row)
                    //当前行可编辑
                    $("#dg").edatagrid("editRow", index)
                } else {
                    alert("请先选中行")
                }
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-help',
            handler: function () {
              $("#dg").edatagrid("saveRow")
            }
        }]
        $('#dg').edatagrid({
            url: '${pageContext.request.contextPath}/banner/findPage',
            //method: "get",
            updateUrl: "${pageContext.request.contextPath}/banner/update",
            destroyUrl:"${pageContext.request.contextPath}/banner/delete",
            columns: [[

                {field: 'id', title: '编号', width: 100},
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }
                },
                {field: 'description', title: '描述', width: 100},
                {field: 'createDate', title: '时间', width: 100,
                    formatter: function(value,row,index) {
                        return timestampToTime(value);
                    }
                }
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 5,
            pageList: [5, 10, 15, 20],
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function(rowIndex, rowData){
                var date=new Date(rowData.createDate);
                var time=date.getFullYear()+"/"+date.getMonth()+"/"+date.getDay();
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + time + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '<p>imgPath: ' + rowData.imgPath + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });
    })
    function submit() {
        $("#ff").form("submit", {
            url: "${pageContext.request.contextPath}/banner/save"
        })
    }
</script>

<table id="dg"></table>
<!-- 添加商品 -->
<div id="dd" class="easyui-dialog" title="上传轮播图" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                     submit();
                      $('#dd').dialog('close');
                      $('#dg').edatagrid('reload')
				}
			},{
				text:'关闭',
				handler:function(){
                     $('#dd').dialog('close');
				}
			}]">
    <form id="ff" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td> 名称:</td>
                <td> <input class="easyui-validatebox" id="title" type="text" name="title" data-options="required:true"/></td>
            </tr>
            <tr>
                <td> 描述:</td>
                <td> <input class="easyui-textbox" type="text" id="description" name="description" data-options=""/></td>
            </tr>
            <tr>
                <td> 状态:</td>
                <td><select id="cc" class="easyui-combobox" name="status" style="width:150px;">
                    <option value="Y">展示</option>
                    <option value="N">不展示</option>
                </select></td>
            </tr>
            <tr>
                <td> 上传图片:</td>
                <td> <input class="easyui-filebox" name="img" style="width:150px"></td>
            </tr>
        </table>
    </form>
</div>

