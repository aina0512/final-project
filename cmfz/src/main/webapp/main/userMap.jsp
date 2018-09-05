<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main_map" style="width: 600px;height:400px;margin-top: 50px;padding-left: 100px"></div>
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
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main_map'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '持名法州App用户分布图',
                subtext: formatTime(new Date())+'   更新数据',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男', '女']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            series: [
                {
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                },
                {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        $.ajax({
            url:"${pageContext.request.contextPath}/user/userMap",
            dataType:"JSON",
            type:"post",
            success:function(nums){
                console.log(nums)
                myChart.setOption({
                    series: [{
                        name: '男',
                        data: nums.man
                    },{
                        name: '女',
                        data: nums.women
                    }]
                })
            }
        })
    </script>

