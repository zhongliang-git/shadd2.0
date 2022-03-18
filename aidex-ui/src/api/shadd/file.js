import request from '@/utils/request2'

// 查询厂家列表
export function uploadFile(file) {
  return request({
    url: '/file/upload',
    method: 'post',
    data: file
  })
}
