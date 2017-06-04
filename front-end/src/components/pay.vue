<template>
    <el-collapse-transition>
        <div id="pay">

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
            fuck:false
            }
        },
        methods: {
            handleTabChange (val) {
                this.activeTab = val
            },
            handleSelect(key, path){
                console.log(key, path);
            },
            showDetail(nowImg, preImg){
                console.log(preImg, nowImg)
            }
        },
        mounted:function(){
        }
    }
</script>

<style lang="scss">
    html, body{
        width:100%;
        height:100%;
    }
    #app{
        width:100%;
        height:100%;
    }
    #pay{

    }
</style>
