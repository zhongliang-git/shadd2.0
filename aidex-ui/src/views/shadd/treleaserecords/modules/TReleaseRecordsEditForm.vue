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
          <a-form-model-item label="厂家" prop="dactoryid">
            <a-input v-model="form.dactoryid" placeholder="请输入厂家"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="产品" prop="productid">
            <a-input v-model="form.productid" placeholder="请输入产品"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货站点" prop="siteid">
            <a-input v-model="form.siteid" placeholder="请输入收货站点"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="收货数量" prop="receivingnumber">
            <a-input-number v-model="form.receivingnumber" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
          </a-form-model-item>
        </a-col>
    </a-row>
    <a-row :gutter="32">
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
import TReleaseRecordsEditForm from './TReleaseRecordsForm'
export default {
  ...TReleaseRecordsEditForm
}
</script>
