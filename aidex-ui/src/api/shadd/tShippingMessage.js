import request from '@/utils/request'

// 查询收货信息列表
export function listTShippingMessage (query) {
  return request({
    url: '/shadd/tShippingMessage/list',
    method: 'get',
    params: query
  })
}

// 查询收货信息详细
export function getTShippingMessage (id) {
  return request({
    url: '/shadd/tShippingMessage/' + id,
    method: 'get'
  })
}

// 新增收货信息
export function addTShippingMessage (data) {
  return request({
    url: '/shadd/tShippingMessage',
    method: 'post',
    data: data
  })
}

// 修改收货信息
export function updateTShippingMessage (data) {
  return request({
    url: '/shadd/tShippingMessage',
    method: 'put',
    data: data
  })
}

// 删除收货信息
export function delTShippingMessage (id) {
  return request({
    url: '/shadd/tShippingMessage/' + id,
    method: 'delete'
  })
}

// 导出收货信息
export function exportTShippingMessage (query) {
  return request({
    url: '/shadd/tShippingMessage/export',
    method: 'get',
    params: query
  })
}
