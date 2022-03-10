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
          <a-form-model-item label="区划" prop="district">
            <a-input v-model="form.district" placeholder="请输入区划"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="联系人" prop="linkman">
            <a-input v-model="form.linkman" placeholder="请输入联系人"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="手机号" prop="mobile">
            <a-input v-model="form.mobile" placeholder="请输入手机号"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="厂家名称" prop="name">
            <a-input v-model="form.name" placeholder="请输入厂家名称"/>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item label="厂家图片" prop="name">
            <a-upload name="file" :beforeUpload="beforeUpload" :showUploadList="false">
              <a-button icon="upload">选择图片</a-button>
            </a-upload>
          </a-form-model-item>
        </a-col>
        <a-col :span="12">
          <a-form-model-item prop="name">
            <div class="avatar-upload-preview" :style="form.image || previews.url? 'width: 100%; height:100%' : 'width: 0px; height:0px;' ">
              <img :src="previews.url" :style="previews.url? 'width: 100%; height:100%' : 'width: 0px; height:0px;' "/>
            </div>
          </a-form-model-item>
        </a-col>
        <a-col :xs="24" :md="12" style="display: none">
          <vue-cropper
            ref="cropper"
            :img="options.img"
            :info="true"
            :autoCrop="options.autoCrop"
            :autoCropWidth="options.autoCropWidth"
            :autoCropHeight="options.autoCropHeight"
            :fixedBox="options.fixedBox"
            @realTime="realTime"
          >
          </vue-cropper>
        </a-col>
          <a-col :span="12">
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
import TFactoryEditForm from './TFactoryForm'
export default {
  ...TFactoryEditForm
}
</script>
