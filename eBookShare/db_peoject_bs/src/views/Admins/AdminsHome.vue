<template>
  <div>
    <!--    <el-col :span="12">-->
    <!--      <div id = "main" style="width: 500px; height: 400px"></div>-->
    <!--    </el-col>-->
    <!--    <el-col :span="12">-->
    <!--      <div id = "pie" style="width: 500px; height: 400px"></div>-->
    <!--    </el-col>-->

    <div>
      <h1>欢迎进入上海大学电子书籍管理系统</h1>
      <br>
      <el-card className="box-card">
        <div>
          <div className="text item "><i className="el-icon-s-home" style="margin:10px"></i>项目名：E-book后端管理系统
          </div>
          <div className="text item"><i className="el-icon-user-solid" style="margin:10px"></i>小组成员：周鹏飞，李洪辰，王唯翰，易津锐
          </div>
          <div className="text item"><i className="el-icon-location" style="margin:10px"></i>上海大学</div>
          <div className="text item"><i className="el-icon-error" style="margin:10px"></i>仅供学习，禁止抄袭</div>
        </div>
      </el-card>
    </div>
    <!--    <el-button type="primary" class="ml-5" @click="EBookup">测试用户上传与审核<i class = "el-icon-bottom"></i></el-button>-->
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: "Home",
  data() {
    return {
      dialogFormVisible: false,
    }
  },
  methods: {
    EBookup() {
      this.$router.push("/admins/ebookupAndAudit")
    },
  },
  // mounted() {//页面元素渲染之后触发
  //   //折线图
  //   var option = {
  //     xAxis: {
  //       type: 'category',
  //       data: []
  //     },
  //     yAxis: {
  //       type: 'value'
  //     },
  //     series: [
  //       {
  //         data: [],
  //         type: 'line'
  //       },
  //       {
  //         data: [],
  //         type: 'bar'
  //       }
  //     ]
  //   };
  //
  //   var chartDom = document.getElementById('main');
  //   var myChart = echarts.init(chartDom);
  //   //请求后端数据
  //   this.request.get("/echarts/example").then(res => {
  //     //填写数据
  //     //这里有两个series表
  //     option.xAxis.data = res.data.x
  //     option.series[0].data = res.data.y
  //     option.series[1].data = res.data.y
  //     //数据准备完毕之后set
  //     myChart.setOption(option)
  //   })
  //
  //   //饼图
  //   var pieoption = {
  //     title: {
  //       text: 'Referer of a Website',
  //       subtext: 'Fake Data',
  //       left: 'center'
  //     },
  //     tooltip: {
  //       trigger: 'item'
  //     },
  //     legend: {
  //       orient: 'vertical',
  //       left: 'left'
  //     },
  //     series: [
  //       {
  //         name: 'Access From',
  //         type: 'pie',
  //         radius: '50%',
  //         data: [
  //           {value: 1048, name: 'Search Engine'},
  //           {value: 735, name: 'Direct'},
  //           {value: 580, name: 'Email'},
  //           {value: 484, name: 'Union Ads'},
  //           {value: 300, name: 'Video Ads'}
  //         ],
  //         emphasis: {
  //           itemStyle: {
  //             shadowBlur: 10,
  //             shadowOffsetX: 0,
  //             shadowColor: 'rgba(0, 0, 0, 0.5)'
  //           }
  //         }
  //       }
  //     ]
  //   };
  //
  //   var pieDom = document.getElementById('pie');
  //   var pieChar = echarts.init(pieDom);
  //   pieChar.setOption(pieoption);
  // }
}
</script>

<style scoped>

</style>