<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">





    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;margin-top: 50px;padding-left: 100px"></div>
    <script type="text/javascript">
        $.ajax({
            url:"${pageContext.request.contextPath}/user/userCount",
            dataType:"JSON",
            type:"post",
            success:function(nums){
                //console.log(nums)
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '持名法州App活跃用户'
                    },
                    tooltip: {},
                    legend: {
                        data:[]
                    },
                    xAxis: {
                        data: ["7天","15天","30天","90天","半年","一年"]
                    },
                    yAxis: {},
                    series: [{
                        name: '活跃用户',
                        type: 'bar',
                        data: nums
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }

        })
    </script>

