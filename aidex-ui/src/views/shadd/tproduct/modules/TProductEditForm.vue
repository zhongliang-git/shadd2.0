<template>
  <a-drawer
    width="45%"
    :title="formTitle"
    :label-col="4"
    :wrapper-col="14"
    :visible="open"
    :body-style="{height:'calc(100vh - 100px)',overflow:'auto'}"
    @close="cancel">
    <a-form-model ref="form" :model="form" :rules="rules" layout="vertical">
      <a-spin :spinning="spinning" :delay="delayTime" tip="Loading...">
      <a-row :gutter="32">
        <a-col :span="12">
          <a-form-model-item label="矿种" prop="minerals">
            <a-input v-model="form.minerals" placeholder="请输入矿种"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="规格型号" prop="specification">
            <a-input v-model="form.specification" placeholder="请输入规格型号"/>
          </a-form-model-item>
        </a-col>
      </a-row>
      </a-spin>
       <div class="bottom-control">
              <a-space>
              <a-button @click="cancel">
                  取消
              </a-button>
              <a-button type="primary" @click="submitForm">
                  保存
              </a-button>
              </a-space>
       </div>
    </a-form-model>
  </a-drawer>
</template>
<script>
import TProductEditForm from './TProductForm'
export default {
  ...TProductEditForm
}
</script>
