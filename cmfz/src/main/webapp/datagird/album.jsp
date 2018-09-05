<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
    $.fn.datebox.defaults.formatter = function(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'/'+m+'/'+d;
    }
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
        $('#treetable').treegrid({
            url:'${ pageContext.request.contextPath }/album/allAlbum',
            idField:'id',
            treeField:'title',
            fit: true,
            fitColumns:true,
            onDblClickRow:function(row){
                $('#audioPlay').dialog({
                    title: '播放列表',
                    width: 450,
                    height: 400,
                    collapsible: true,
                    minimizable: true,
                    maximizable: true,
                });
                $("audio").prop("src","${ pageContext.request.contextPath }/upload/"+row.audioPath);
            },
            columns:[[
                {field: 'title', title: '名称', width: 60},
                {field: 'duration', title: '时长', width: 80},
                {field: 'size', title: '章节大小', width: 80},
                {field: 'audioPath', title: '下载路径', width: 80}
            ]],
            toolbar: [{
                iconCls: 'icon-tip',
                text:"专辑详情",
                handler: function(){
                    //获取当前节点的节点对象。
                    var selectedRow= $('#treetable').treegrid('getSelected');
                    if( selectedRow!=null ){
                        //判断选中的是根节点还是父节点
                        if(selectedRow.author==null ){
                            $.messager.alert('警告','请选择要查看相对应的专辑!');
                        }else{
                            //给表单添加默认数据
                            $('#Ablums').form('load',{
                                id:selectedRow.id,
                                author:selectedRow.author,
                                brife:selectedRow.brife,
                                count:selectedRow.count,
                                score:selectedRow.score,
                                broadCast:selectedRow.broadCast,
                                title:selectedRow.title,
                                publicDate:formatTime(selectedRow.publicDate),
                            });
                            $("#corverImg").prop("src","${pageContext.request.contextPath}/upload/"+selectedRow.corverImg);
                            $('#Albumdatails').dialog({
                                title: '专辑详情',
                                width: 450,
                                height: 400,
                                collapsible: true,
                                minimizable: true,
                                maximizable: true,
                                modal: true,//遮罩效果
                            });
                        }
                    }else{
                        $.messager.alert('警告','请选择要查看的专辑!');
                    }
                }
            },'-',{
                iconCls: 'icon-add',
                text:"添加专辑",
                handler: function(){
                    $('#addAblum').dialog({
                        title: '添加专辑',
                        width: 450,
                        height: 400,
                        collapsible: true,
                        minimizable: true,
                        maximizable: true,
                        modal: true,//遮罩效果
                        buttons:[{
                            text:'重置',
                            iconCls:'icon-reload',
                            handler:function(){
                                $("#Album").form('clear');
                            },
                        },{
                            text:'提交',
                            iconCls:'icon-ok',
                            handler:function(){
                                $("#Album").submit();
                            },
                        }],
                    });
                    //进行表单的初始化
                    $("#Album").form({
                        url:'${ pageContext.request.contextPath }/album/addAlbum',
                        onSubmit:function(){
                            var result = $("#Album").form('validate');
                            if(result) return true;
                            else return false;
                        },
                        success:function(data){
                            $("#addAblum").dialog('close');//关闭对话框
                            $('#treetable').treegrid('reload');//数据表格的重新加载
                        },
                    });
                }
            },'-',{
                iconCls: 'icon-add',
                text:"添加章节",
                handler: function(){
                    var row= $('#treetable').treegrid('getSelected');
                    console.log(row)
                    if(row!=null){
                        if(row.score!=null){
                            $('#addChap').dialog({
                                title: '添加章节',
                                width: 450,
                                height: 300,
                                collapsible: true,
                                minimizable: true,
                                maximizable: true,
                                modal: true,//遮罩效果
                                buttons:[{
                                    text:'重置',
                                    iconCls:'icon-reload',
                                    handler:function(){
                                        $("#Chap").form('clear');
                                    },
                                },{
                                    text:'提交',
                                    iconCls:'icon-ok',
                                    handler:function(){
                                        $("#Chap").submit();
                                    },
                                }],
                            });

                            //进行表单的初始化
                            $("#Chap").form({
                                url:'${ pageContext.request.contextPath }/chap/addChap',
                                onSubmit:function(){
                                    var result = $("#Chap").form('validate');
                                    if(result) return true;
                                    else return false;
                                },
                                success:function(data){
                                    $("#addChap").dialog('close');//关闭对话框
                                    $('#treetable').treegrid('reload');//数据表格的重新加载
                                },
                            });
                            //给表单添加默认数据
                            $('#Chap').form('load',{
                                albumName:row.title,
                                parentId:row.id,
                            });
                        }else{
                            $.messager.alert('警告','请选择专辑名称!');
                        }
                    }else{
                        $.messager.alert('警告','请选择您要添加章节的相对应的专辑名称!');
                    }
                },
            },'-',{
                iconCls: 'icon-undo',
                text:"下载音频",
                handler: function(){
                    var row= $('#treetable').treegrid('getSelected');
                    if(row==null || row.author!=null)
                        $.messager.alert('警告','请选择您要下载的音频文件!');
                    else{
                        location.href="${ pageContext.request.contextPath }/chap/download?url="+row.audioPath+"&name="+row.title;
                    }
                }
            }]
        });
    });
</script>
<table id="treetable" style="width:600px;height:400px"></table>
<div id="Albumdatails" style="padding-left: 90px; padding-top: 35px">
    <form id="Ablums" style="left: 50px">
        <table cellpadding="2" cellspacing="4" border="1">
            <tr style="display: none">
                <td valign="middle" align="right">编号:</td>
                <td valign="middle" align="left" >
                    <input type="text" name="id" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">专辑名称:</td>
                <td valign="middle" align="left">
                    <input name="title" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">作者:</td>
                <td valign="middle" align="left">
                    <input name="author" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">内容简介:</td>
                <td valign="middle" align="left">
                    <input name="brife" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">章节数:</td>
                <td valign="middle" align="left">
                    <input name="count" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">封面:</td>
                <td valign="middle" align="left">
                    <img id="corverImg" src="" name="corverImg" style="width: 100px;height: 50px" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">评分:</td>
                <td valign="middle" align="left">
                    <input name="score" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">播音:</td>
                <td valign="middle" align="left">
                    <input name="broadCast" readonly/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">发布日期:</td>
                <td valign="middle" align="left">
                    <input name="publicDate" readonly/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="addAblum" style="padding-left: 90px; padding-top: 35px">
    <form id="Album" method="post" style="left: 50px" enctype="multipart/form-data">
        <table cellpadding="2" cellspacing="4" border="1">
            <tr>
                <td valign="middle" align="right">专辑名称:</td>
                <td valign="middle" align="left">
                    <input class="easyui-textbox" data-options="required:true" name="title"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">作者:</td>
                <td valign="middle" align="left">
                    <input name="author" class="easyui-textbox" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">内容简介:</td>
                <td valign="middle" align="left">
                    <input name="brife" class="easyui-textbox" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">章节数:</td>
                <td valign="middle" align="left">
                    <input name="count" class="easyui-numberbox" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">封面:</td>
                <td valign="middle" align="left">
                    <input name="corverImgPath" class="easyui-filebox" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">评分:</td>
                <td valign="middle" align="left">
                    <input name="score" class="easyui-textbox" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">播音:</td>
                <td valign="middle" align="left">
                    <input name="broadCast" class="easyui-textbox" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">发布日期:</td>
                <td valign="middle" align="left">
                    <input name="publicDate" class="easyui-datebox" data-options="required:true"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="addChap" style="padding-left: 90px; padding-top: 50px">
    <form id="Chap" method="post" style="left: 50px" enctype="multipart/form-data">
        <table cellpadding="2" cellspacing="4" border="1">
            <tr>
                <td valign="middle" align="right">章节名称:</td>
                <td valign="middle" align="left">
                    <input class="easyui-textbox" data-options="required:true" name="title"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">文件路径:</td>
                <td valign="middle" align="left">
                    <input name="audio_Path" class="easyui-filebox" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">隶属的专辑:</td>
                <td valign="middle" align="left">
                    <input name="albumName" class="easyui-textbox" data-options="required:true"/>
                </td>
            </tr>
            <tr style="display: none">
                <td valign="middle" align="right">隶属专辑的Id:</td>
                <td valign="middle" align="left">
                    <input name="parentId" class="easyui-textbox" data-options="required:true"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="audioPlay" style="text-align: center;padding-top: 300px">
    <audio src="" autoplay="autoplay" controls="controls"></audio>
</div>
</html>
