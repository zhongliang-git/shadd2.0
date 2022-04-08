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
          <a-form-model-item label="采购产品" prop="productId">
            <a-input v-model="form.productId" placeholder="请输入采购产品"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="单价" prop="price">
            <a-input-number v-model="form.price" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="配送费" prop="shippingFee">
            <a-input-number v-model="form.shippingFee" :min="0" style="width: 100%"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="数量" prop="num">
            <a-input v-model="form.num" placeholder="请输入数量"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="小计" prop="total">
            <a-input-number v-model="form.total" :min="0" style="width: 100%"/>
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
import TOrderDetailEditForm from './TOrderDetailForm'
export default {
  ...TOrderDetailEditForm
}
</script>
