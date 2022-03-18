import request from '@/utils/request'

// 查询运线列表
export function listTShippingLine (query) {
  return request({
    url: '/shadd/tShippingLine/list',
    method: 'get',
    params: query
  })
}

// 查询运线详细
export function getTShippingLine (id) {
  return request({
    url: '/shadd/tShippingLine/' + id,
    method: 'get'
  })
}

// 新增运线
export function addTShippingLine (data) {
  return request({
    url: '/shadd/tShippingLine',
    method: 'post',
    data: data
  })
}

// 修改运线
export function updateTShippingLine (data) {
  return request({
    url: '/shadd/tShippingLine',
    method: 'put',
    data: data
  })
}

// 删除运线
export function delTShippingLine (id) {
  return request({
    url: '/shadd/tShippingLine/' + id,
    method: 'delete'
  })
}

// 导出运线
export function exportTShippingLine (query) {
  return request({
    url: '/shadd/tShippingLine/export',
    method: 'get',
    params: query
  })
}
