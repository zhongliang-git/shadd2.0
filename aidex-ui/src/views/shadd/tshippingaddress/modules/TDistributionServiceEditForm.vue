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
          <a-form-model-item label="站点Id" prop="site">
            <a-input v-model="form.site" placeholder="请输入站点Id"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="配送费" prop="shippingFee">
            <a-input-number v-model="form.shippingFee" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="24">
          <a-form-model-item label="备注" prop="remark">
            <a-textarea v-model="form.remark" placeholder="请输入备注" :rows="4"/>
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
import TDistributionServiceEditForm from './TDistributionServiceForm'
export default {
  ...TDistributionServiceEditForm
}
</script>
