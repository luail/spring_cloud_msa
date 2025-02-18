<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="2" md="4">
                <v-card>
                    <v-card-title class="text-h5 text-center">
                        로그인
                    </v-card-title>
                    <v-card-text>
                        <v-form @keydown.enter="doLogin">
                            <v-text-field
                            label="email"
                            v-model="email"
                            type="email"
                            prepend-icon="mdi-email"
                            required
                            />
                            <v-text-field
                            label="password"
                            v-model="password"
                            type="password"
                            prepend-icon="mdi-lock"
                            required
                            />
                            <v-row>
                                <v-col cols="12">
                                    <v-btn color="#c0c1ff" block @click="doLogin()" @keyup.enter="doLogin()">로그인</v-btn>
                                </v-col>
                            </v-row>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import axios from 'axios';

export default {
    data() {
        return {
            email:"",
            password:""
        }
    },
    methods: {
        async doLogin() {
            // json으로 보내는 방법은 객체를 만들어주면 된다.
            const loginData = {email:this.email, password:this.password};
            const response = await axios.post(`${process.env.VUE_APP_API_BASE_URL}/member/doLogin`, loginData)
            const token = response.data.token
            const refreshToken = response.data.refreshToken
            localStorage.setItem('token', token) //왼쪽의 토큰은 키값, 오른쪽의 토큰은 받아온 토큰을 밸류로 세팅.
            localStorage.setItem('refreshToken', refreshToken)
            window.location.href = '/';
        }
    }
}
</script>