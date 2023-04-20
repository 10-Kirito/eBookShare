<template>
  <div>
    <el-container>
      <!--   首页头部   -->
      <el-header>
        <el-col :span="12" :offset="1" align="left"><div class="grid-content bg-purple">{{ pageTitle }}</div></el-col>
        <el-row type="flex" justify="end">
          <el-col :span="8"><router-link to="/">首页</router-link></el-col>
          <el-col :span="8"><router-link to="/">个人书架</router-link></el-col>
          <el-col :span="8"><router-link to="/">登录</router-link></el-col>
        </el-row>
      </el-header>

      <!--  首页主要组成部分-->
      <el-main class="el-main">
        <div style="margin-bottom: 800px; margin-top: 20px">
          <input type="text" v-model="searchQuery" placeholder="输入关键字搜索图书">
          <button @click="searchBooks">查询</button>
        </div>
        <div>
          <li style="height: 10px" v-for="book in searchedBooks" :key="book.id">
            {{ book.title }}
            {{ book.author }}
            {{ book.description }}
          </li>
        </div>
      </el-main>

      <!--  首页下半部分-->
      <el-footer height="100px">

      </el-footer>
    </el-container>
  </div>
</template>



<script>
export default {
  name: "Layout",
  data() {
    return {
      pageTitle: '上海大学电子图书分享平台',
      books: [
        { id: 1, title: 'Vue实战', author: '张三', description: '本书介绍Vue的实际应用场景' },
        { id: 2, title: 'JavaScript高级编程', author: '李四', description: '本书详细介绍JavaScript的高级特性' },
        { id: 3, title: '深入浅出Webpack', author: '王五', description: '本书介绍Webpack的工作原理和实际应用' },
      ],
      searchQuery: '',
    };
  },
  computed: {
    searchedBooks() {
      if (this.searchQuery === '') {
        return this.books;
      } else {
        const query = this.searchQuery.toLowerCase();
        return this.books.filter(book => {
          const title = book.title.toLowerCase();
          const author = book.author.toLowerCase();
          const description = book.description.toLowerCase();
          return title.includes(query) || author.includes(query) || description.includes(query);
        });
      }
    },
  },
  methods: {
    searchBooks() {
      // 这里可以向服务器发起搜索请求，获取匹配的图书列表
    },
  },
}
</script>

<style scoped>
.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}


.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  /*line-height: 700px;*/
}

body > .el-container {
  margin-bottom: 40px;
}
h1 {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 16px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 16px;
}

h2 {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 8px;
}

p {
  font-size: 16px;
  margin: 0;
}

input[type="text"] {
  font-size: 16px;
  padding: 8px;
  margin-right: 8px;
  width: 700px;
  height: 50px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  font-size: 16px;
  padding: 8px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  height: 50px;
  width: 100px;
}
</style>

