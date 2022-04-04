import request from '@/utils/request'

// 查询收货地址列表
export function listTShippingAddress (query) {
  return request({
    url: '/shadd/tShippingAddress/list',
    method: 'get',
    params: query
  })
}

// 查询收货地址详细
export function getTShippingAddress (id) {
  return request({
    url: '/shadd/tShippingAddress/' + id,
    method: 'get'
  })
}

// 新增收货地址
export function addTShippingAddress (data) {
  return request({
    url: '/shadd/tShippingAddress',
    method: 'post',
    data: data
  })
}

// 修改收货地址
export function updateTShippingAddress (data) {
  return request({
    url: '/shadd/tShippingAddress',
    method: 'put',
    data: data
  })
}

// 删除收货地址
export function delTShippingAddress (id) {
  return request({
    url: '/shadd/tShippingAddress/' + id,
    method: 'delete'
  })
}

// 导出收货地址
export function exportTShippingAddress (query) {
  return request({
    url: '/shadd/tShippingAddress/export',
    method: 'get',
    params: query
  })
}
