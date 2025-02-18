<template>
<h1>vue 조건식</h1>
<button v-if="!isLogined" @click="doLogin()">로그인</button>
<button v-if="isLogined" @click="doLogout()">로그아웃</button>
<div v-if="!isLogined">로그인해주세요</div>
<div v-if="isLogined">환영합니다 고갱님</div>

<h1>상품목록조회</h1>
<v-simple-table>
    <thead>
        <tr>
            <th>id</th><th>이름</th><th>가격</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="p in productList" :key="p.id">
            <td>{{ p.id }}</td><td>{{ p.name }}</td><td>{{ p.price }}</td>
        </tr>
    </tbody>
</v-simple-table>
</template>
<script>
import axios from 'axios';
export default {
    data(){
        return {
            isLogined:false,
            productList:[{id:1, name:"apple", price:"3000"},{id:2, name:"banana", price:"2000"},{id:3, name:"ki", price:"10000"}]
        } 
    },
    async created() {
        // 서버실행
        // axios.get요청
        // productList = response.data (데이터구조 잘 모를경우 console로 조회해서 확인.)
        const response = await axios.get("http://localhost:8080/product/list")
        this.productList = response.data.content
    },
    methods:{
        doLogin() {
            this.isLogined=true;
        },
        doLogout() {
            this.isLogined=false;
        }
    },
}
</script>