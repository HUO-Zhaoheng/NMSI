<template>
    <el-collapse-transition>
        <div id="index" style="padding-top:10px;">
            <el-row :gutter="20">
                <el-col :span="6" :offset="14">
                    <mu-tabs :value="headerTab" @change="changeHeaderTab" style="background-color:#1E2339;">
                        <mu-tab value="home" title="Home"/>
                        <mu-tab v-if="isRegister" value="profile" title="Profile"/>
                    </mu-tabs>
                </el-col>
                <el-col :span="4" :offset="0">
                    <router-link to="/">
                        <mu-raised-button label="登陆 | 注册" class="mu-button" primary/>
                    </router-link>
                </el-col>
            </el-row>
            <br/>
            <div style="text-align:center; color:white; font-size:2em;"> Your weekend buddy for this week </div>
            <br/>
            <el-carousel type="card" height="30vw" :autoplay="false" @change="handleSelect" style="margin:0 15% 0 15%;">
                <el-carousel-item v-for="(item, index) in hotMovies" :key="index">
                    <mu-card @click.native="showDetail(index)" class="index_carousel_card" style="background-color:black;margin:0; padding:0; height:100%;">
                        <img style="width:100%;height:100%;" :src="item.source">
                        <div class="index_carousel_card_trailer-button" v-show="item.wider">
                            <mu-float-button ref="demo" @click="bookMovie" tooltip="watch trailer" icon="info" secondary/>
                            <div style="color:white;">购票</div>
                        </div>
                        <mu-card class="index_carousel_card_detail" v-bind:class="{ narrow: !item.wider, wide :item.wider}">
                            <div style="font-size:25px;text-align:left;margin:12px;">{{item.title}}</div>
                            <mu-divider style="width:100%; margin:0 auto;" :shallowInset="true"/>
                            <div style="display:flex;flex-direction:row;" >
                                <div style="text-align:left; padding-left:10px;width:38%;margin-right:2px;">
                                    <span style="color:#D4D1D3;">release date</span><br/>
                                    <span>{{item.year}}</span><br/><br/>
                                    <span style="color:#D4D1D3;">Directed by</span><br/>
                                    <div v-for="(director,index) in item.directors" :key="index">
                                        <span>{{director.name}}</span><br/>
                                    </div>
                                    <span style="color:#D4D1D3;">Starring</span><br/>
                                    <div v-for="(cast,index) in item.casts" :key="index">
                                        <span>{{cast.name}}</span><br/>
                                    </div>
                                </div>
                                <div style="text-align:left; padding-left:0px;width:60%;">
                                    <span style="color:#D4D1D3;">Genre</span><br/>
                                    <span v-for="(genre,index) in item.genres" :key="index">{{genre}}&nbsp;</span>
                                    <br />
                                    <span style="color:#D4D1D3;">Description</span><br/>
                                    <mu-card-text style="padding:0;">
                                        暂无描述
                                    </mu-card-text>
                                </div>
                            </div>
                            <mu-divider style="width:100%; margin:0 auto;" :shallowInset="true"/>
                            <div style="margin-top:25px">
                                <mu-icon value="grade" v-for="_item in 10" :color="_item < item.star ? 'yellow' : 'grey'" :key="item"/>
                            </div>
                            <span style="color:#D4D1D3;">IMDB</span>
                        </mu-card>
                    </mu-card>
                </el-carousel-item>
            </el-carousel>
            <br/>
            <el-row :gutter="20" style="height:80px">
                <el-col :span="5" :offset="1" style="height:100%;font-size:20px;font-weight:bold;color:white;line-height:50px;">Discover movies</el-col>
                <el-col :span="6" :offset="3">
                    <mu-tabs :value="categoryTab" @change="changeCategoryTab" style="background-color:#1E2339;">
                        <mu-tab value="top20" title="Top20"/>
                        <mu-tab value="coming" title="Coming soon"/>
                        <mu-tab value="us" title="US hot"/>
                    </mu-tabs>
                </el-col>
                <el-col :span="6" :offset="3" style="display:flex;flex-direction:row;justify-content:flex-end;">
                    <mu-icon @click="searchMovie" value="search" color="white" style="line-height:180%;cursor:pointer;"/>
                    <mu-text-field v-model="searchInfo" @keyup.enter.native="searchMovie" class="appbar-search-field index_search" hintText="请输入搜索内容" hintTextClass="index_search" style="width:120px !important;"/>
                </el-col>
            </el-row>
            <br/> 
            <mu-flexbox wrap="wrap" justify="center">
                <mu-card @click.native="openDialog(item)" v-for="item in categoryMovies" :key="item" style="width:200px; height:300px; margin:20px; border-radius:2em;">
                    <img :src="item.source" style="width:200px;height:300px;"/>
                </mu-card>
            </mu-flexbox>
            <el-dialog title="影片详情" :visible.sync="showDialog" v-if="showDialog" custom-class="dialog">
                <mu-card class="index_dialog_card" style="background-color:#1E2350; margin:0; padding:0;height:40vw;">
                    <img style="width:40%; height:100%;float:left;" :src="dialog.source">
                    <mu-card class="index_dialog_card_detail">
                        <div style="font-size:25px;text-align:left;margin:12px;">{{dialog.title}}</div>
                        <mu-divider style="width:100%; margin:0 auto;" :shallowInset="true"/>
                        <div style="display:flex;flex-direction:row;" >
                            <div style="text-align:left; padding-left:10px;width:38%;margin-right:2px;">
                                <span style="color:#D4D1D3;">release date</span><br/>
                                <span>{{dialog.year}}</span><br/><br/>
                                <span style="color:#D4D1D3;">Directed by</span><br/>
                                <div v-for="(director,index) in dialog.directors" :key="index">
                                    <span>{{director.name}}</span><br/>
                                </div>
                                <span style="color:#D4D1D3;">Starring</span><br/>
                                <div v-for="(cast,index) in dialog.casts" :key="index">
                                    <span>{{cast.name}}</span><br/>
                                </div>
                            </div>
                            <div style="text-align:left; padding-left:0px;width:60%;">
                                <span style="color:#D4D1D3;">Genre</span><br/>
                                <span v-for="(genre,index) in dialog.genres" :key="index">{{genre}}&nbsp;</span>
                                <div class="index_dialog_card_trailer-button">
                                    <mu-float-button @click="bookMovie" icon="info" secondary/>
                                    <div>购票</div>
                                </div>
                            </div>
                        </div>
                        <mu-divider style="width:100%; margin:0 auto;" :shallowInset="true"/>
                        <div style="margin-top:25px">
                            <mu-icon value="grade" v-for="_item in 10" :color="_item < dialog.star ? 'yellow' : 'grey'" :key="_item"/>
                        </div>
                        <span style="color:#D4D1D3;">IMDB</span>
                    </mu-card>
                </mu-card>
            </el-dialog>
            <el-dialog :title="dialog.title" :visible.sync="showBooking" v-if="showBooking" custom-class="dialog">
                <el-row>
                    <el-select v-model="cinema" placeholder="请选择">
                        <el-option
                        v-for="item in cinemas"
                        :key="item"
                        :label="item"
                        :value="item">
                        </el-option>
                    </el-select>
                    <el-date-picker
                        v-model="day"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy-MM-dd"
                        :picker-options="{
                            disabledDate(time) {
                                return time.getTime() < Date.now() - 8.64e7;
                            }
                        }">
                    </el-date-picker>
                    <el-time-select
                        v-model="time"
                        :picker-options="{
                            start: '10:00',
                            step: '02:30',
                            end: '22:30'
                        }"
                        placeholder="选择时间">
                    </el-time-select>
                    <el-button>确认</el-button>
                </el-row><br/>
                <mu-card class="index_dialog_card" style="margin:0; padding:0;">
                    <div>
                        <span><mu-icon value="movie" :size="32" color="red"/>&nbsp;已选择</span>    
                        <span><mu-icon value="movie" :size="32"/>&nbsp;未选择</span>
                        <span><mu-icon value="movie" :size="32" color="blue"/>&nbsp;已被预定</span>    
                    </div>

                    <el-row v-for="item in 12" :key="item">
                            <mu-icon @click="book(_item, item)" v-for="_item in 22" value="movie" :size="24" :key="_item" :class="{seat:fuck[22*(item-1) + _item-1]}"/>
                    </el-row>
                    <el-button @click="finishBooking">确认购票</el-button>
                </mu-card>
            </el-dialog>
        </div>
    </el-collapse-transition>
</template>

<script>
    import Swiper from "swiper"
    import { Loading } from 'element-ui';
    import $ from "jquery"
    export default {
        name: 'app',
        data () {
            return {
                headerTab: 'home',
                categoryTab: 'top20',
                isRegister: false,
                showDialog:false,
                index_carousel_card_detail_width:"narrow",
                //指示当前走马灯中显示的是哪一张
                img:0,
                hotMovies:[],
                top250Movies:[],
                comingMovies:[],
                USHotMovies:[],
                categoryMovies:[],
                dialog:null,
                searchInfo:"",
                day:new Date(),
                time:"",
                cinema:"",
                cinemas:["正佳广场","飞扬"],
                showBooking:false,
                fuck:[]
            }
        },
        methods: {
            searchMovie(val){
                this.$http.jsonp("http://api.douban.com//v2/movie/search?q=" + this.searchInfo)
                    .then((res) => {
                        console.log(res.data)
                        let temp = []  
                        res.data.subjects.forEach((element) => {
                            temp.push({
                                source:element.images.large,
                                wider:false,
                                star: element.rating.average,
                                casts:element.casts,
                                directors:element.directors,
                                year:element.year,
                                genres:element.genres,
                                title:element.original_title
                            })
                        })
                        this.categoryMovies = temp
                    })
                    .catch((error) => {
                        console.log(error)
                    })
            },
            changeHeaderTab (val) {
                this.headerTab = val
            },
            changeCategoryTab(val) {
                this.categoryTab = val
                switch(val) {
                    case "top20":
                        this.categoryMovies = this.top250Movies
                        break
                    case "coming":
                        this.categoryMovies = this.comingMovies
                        break
                    case "us":
                        this.categoryMovies = this.USHotMovies
                        break
                }
            },
            handleSelect(key, path){
                this.img=key
                for (let i in this.hotMovies) 
                    this.hotMovies[i].wider = false
            },
            showDetail(index){
                if (this.img == index) {
                    this.hotMovies[this.img].wider = !this.hotMovies[this.img].wider
                }
            },
            bookMovie(e){  
                e.stopPropagation()
                this.showBooking = true
                this.showDialog = false
            },
            openDialog(val){
                this.dialog = val
                this.showDialog = true
            },
            finishBooking(){
                this.$message({
                    message:"恭喜你, 预定"+ this.dialog.title + "成功",
                    type:"success"
                })
            },
            book(val, v){
                let temp = []
                for (let i = 0; i < 264; i++)
                    if (i == (val+22*(v-1)-1) || this.fuck[i] == true)
                        temp[i] = true
                this.fuck = temp
            }
        },
        created: function() {
            for (let i = 0; i < 264; i++) {
                this.fuck[i] = false
            }
            var f = (data, type, count) => {
                data.forEach((element,index) => {
                    if (type == "USHotMovies") {
                        this[type].push({
                            source:element.subject.images.large,
                            wider:false,
                            star:element.subject.rating.average,
                            casts:element.subject.casts,
                            directors:element.subject.directors,
                            year:element.subject.year,
                            genres:element.subject.genres,
                            title:element.subject.original_title
                        })       
                    } else {
                        if (index < count)
                            this[type].push({
                                source:element.images.large,
                                wider:false,
                                star: element.rating.average,
                                casts:element.casts,
                                directors:element.directors,
                                year:element.year,
                                genres:element.genres,
                                title:element.original_title
                            })
                    }
                })  
            }
            let loadingInstance = Loading.service(
                {
                    fullscreen: true ,
                    customClass:"loading"
                })
            this.$http.jsonp("http://api.douban.com/v2/movie/in_theaters")
                .then((res) => {  
                    f(res.data.subjects, "hotMovies", 5)
                    return "fuck"
                })
                .then((data) => {
                    this.$http.jsonp("http://api.douban.com/v2/movie/top250")
                        .then((res) => {  
                            f(res.data.subjects, "top250Movies", 20)
                            this.categoryMovies = this.top250Movies
                            return "fuck"
                        }).catch((res) => {console.log(res)})
                })
                .then((data) => {
                    this.$http.jsonp("http://api.douban.com/v2/movie/coming_soon")
                        .then((res) => {  
                            f(res.data.subjects, "comingMovies", 20)
                            return "fuck"
                        }).catch((res) => {console.log(res)})   
                })
                .then((data) => {
                    this.$http.jsonp("http://api.douban.com/v2/movie/us_box")
                        .then((res) => {  
                            f(res.data.subjects, "USHotMovies")
                            this.dialog = this.hotMovies[0]
                            loadingInstance.close()
                            return "fuck"
                        }).catch((res) => {console.log(res)})   
                })
                .catch((res) => {   
                    console.log(res)   
                })
        },
        mounted:function(){
        }
    }
</script>

<style lang="scss">
    .seat{
        color:red;
    }
    .el-dialog--small{
        width:80% !important;
    }
    .dialog{
        .index_dialog_card{
            position: relative;
            .index_dialog_card_trailer-button{
                width:100%;
            }
            .index_dialog_card_detail{
                padding:2% 5% 2% 5%;
                position: absolute;
                text-align:center;
                top:0;
                right:0;
                width:60%;
                height:100%;
                background-color: #FEFDFD;
                transition: transform 0.8s;
                overflow:scroll;
            }
        }
    }
    .loading{
        background-color: rgba(0,0,0,0.9)
    }
    .narrow{
        transform:translate(100%, 0);
    }
    .wide{
    }
    html, body{
        width:100%;
        height:100%;
    }
    #app{
        width:100%;
        height:100%;
    }
    .mu-button{
        width:120px;
        border-radius: 2em !important;
        margin:10px;
        color:white;
    }
    #index{
        text-align:center !important;
        background-color:#1E2339;
        width:100%;
        .el-menu{
            background-color:#1E2339;
            width:100%;
        }
        .el-carousel__item {
            border-radius: 2em;
            .h3 {
                color: #475669;
                font-size: 14px;
                opacity: 0.75;
                line-height: 200px;
                margin: 0;
                border-radius: 2em;
            }
            .index_carousel_card{
                position: relative;
                .index_carousel_card_trailer-button{
                    position:absolute;
                    top:50%;
                    left:6%;
                    transform: translate(0,-50%);
                }
                .index_carousel_card_detail{
                    padding:2% 5% 2% 5%;
                    position: absolute;
                    text-align:center;
                    top:0;
                    right:0;
                    width:69%;
                    height:100%;
                    background-color: #FEFDFD;
                    transition: transform 0.8s;
                    overflow:scroll;
                }
            }
        }
        .index_search{
            color:white;
            input{
                color:white !important;
            }
        }
    }
</style>
