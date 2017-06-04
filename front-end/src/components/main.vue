<template>
    <el-collapse-transition>
        <div id="index" style="padding-top:10px;">
            <el-row :gutter="20">
                <el-col :span="10" :offset="9">
                    <mu-tabs :value="activeTab" @change="handleTabChange" style="background-color:#1E2339;">
                        <mu-tab value="tab1" title="Home"/>
                        <mu-tab value="tab2" title="Discover"/>
                        <mu-tab value="tab3" title="About"/>
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
            <el-carousel type="card" height="30vw" :autoplay="fuck" @change="handleSelect" style="margin:0 6% 0 6%;">
                <el-carousel-item v-for="(item, index) in hotMovies" :key="index">
                    <mu-card @click.native="shit(index)" class="index_carousel_card" style="background-color:black;margin:0; padding:0; height:100%;">
                        <img style="width:100%;height:100%;" :src="item.source">
                        <mu-card class="index_carousel_card_detail" v-bind:class="{ narrow: !item.wider, wide :item.wider}">
                            <div style="font-size:25px;text-align:left;height:12%;">Deep dark fantasy</div>
                            <mu-divider style="width:100%; margin:0 auto;" :shallowInset="true"/>
                            <el-row style="height:58%;overflow:scroll;" >
                                <el-col :span="8" style="text-align:left; padding-left:10px;">
                                    <span style="color:#D4D1D3;">release date</span>
                                    <br/>
                                    <span>2018.8.01</span>
                                    <br/>
                                    <br/>
                                    <span style="color:#D4D1D3;">Directed by</span>
                                    <br/>
                                    <span>Tong Dawei</span>
                                    <br/>
                                    <br/>
                                    <span style="color:#D4D1D3;">Starring</span>
                                    <br/>
                                    <span>Bill Harryton</span>
                                </el-col>
                                <el-col :span="16" style="text-align:left; padding-left:0px;">
                                    <span style="color:#D4D1D3;">Genre</span>
                                    <br/>
                                    <span>Kongfu Education</span>
                                    <br/>
                                    <br/>
                                    <mu-card-text style="padding:0;">
                                        My name is Van, I'm an artist, I'm a performance artist. 
                                        I'm hired for people to fulfill their fantasies, their deep dark fantasies.
                                    </mu-card-text>
                                    <mu-raised-button @click="fucker" label="see more" class="mu-button" style="font-size:2px;" backgroundColor="white" color="#E65C6A"primary/>
                                </el-col>
                            </el-row>
                            <mu-divider style="width:100%; margin:0 auto;" :shallowInset="true"/>
                            <el-row style="height:30%;">
                                <el-col :span="8">
                                    <mu-card-title title="7/10" subTitle="IMDB"/>
                                </el-col>
                                <el-col :span="8">
                                    <mu-card-title title="67%" subTitle="metacritic"/>
                                </el-col>
                                <el-col :span="8">
                                    <div @click="fucker" style="margin-top:25px">
                                        <el-rate
                                        v-model="value1"
                                        disabled>
                                        </el-rate>
                                    </div>
                                    
                                </el-col>
                            </el-row>
                            <mu-card-actions>
                                <mu-flat-button label="Action 1"/>
                                <mu-flat-button label="Action 2"/>
                            </mu-card-actions>
                        </mu-card>
                    </mu-card>
                </el-carousel-item>
            </el-carousel>
            <br/>
            <el-row :gutter="20" style="height:80px">
                <el-col :span="5" :offset="1" style="height:100%;font-size:20px;font-weight:bold;color:white;line-height:50px;">Discover movies</el-col>
                <el-col :span="6" :offset="3">
                    <mu-tabs :value="activeTab" @change="handleTabChange" style="background-color:#1E2339;">
                        <mu-tab value="tab1" title="Random"/>
                        <mu-tab value="tab2" title="Popular"/>
                        <mu-tab value="tab3" title="Recent"/>
                    </mu-tabs>
                </el-col>
                <el-col :span="6" :offset="3" style="display:flex;flex-direction:row;justify-content:flex-end;">
                    <mu-icon value="search" color="white" style="line-height:180%;"/>
                    <mu-text-field class="appbar-search-field index_search" hintText="请输入搜索内容" hintTextClass="index_search" style="width:120px !important;"/>
                </el-col>
            </el-row>
            <br/>
            <mu-flexbox wrap="wrap" justify="center">
                <mu-card v-for="item in 9" :key="item" style="width:200px; height:300px; margin:20px; border-radius:2em;">
                    <!--<img :src="hotMovies[0].source" style="width:200px;height:300px;"/>-->
                </mu-card>
            </mu-flexbox>
            <mu-raised-button label="发现更多电影" class="mu-button" primary style="width:20vw;"/>
        </div>
    </el-collapse-transition>
</template>

<script>
    import Swiper from "swiper"
    export default {
        name: 'app',
        data () {
            return {
                activeTab: 'tab1',
                activeIndex:"1",
                fuck:false,
                index_carousel_card_detail_width:"narrow",
                img:0,
                hotMovies:[],
                value1:4
            }
        },
        methods: {
            handleTabChange (val) {
                this.activeTab = val
            },
            handleSelect(key, path){
                this.img=key
                for (let i in this.hotMovies) 
                    this.hotMovies[i].wider = false
            },
            shit(index){
                if (this.img == index) {
                    this.hotMovies[this.img].wider = !this.hotMovies[this.img].wider
                }
            },
            fucker(e){
                console.log(e)
                e.stopPropagation()
            }
        },
        mounted:function(){
            for (let i = 0; i < 5; i++) {
                this.hotMovies.push({
                    source:"https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1496481980&di=e683d3e300d0506811aecbcb1ffbe1eb&src=http://i2.hdslb.com/video/07/079f9b135756a966d629308e2b053a74.jpg",
                    wider:false
                })
            }
        }
    }
</script>

<style lang="scss">
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
                .index_carousel_card_detail{
                    padding:2% 5% 2% 5%;
                    position: absolute;
                    text-align:center;
                    top:0;
                    right:0;
                    width:70%;
                    height:100%;
                    background-color: #FEFDFD;
                    transition: transform 0.8s;
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
