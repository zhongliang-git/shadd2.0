import request from '@/utils/request'

// 查询产品列表
export function listTProduct (query) {
  return request({
    url: '/shadd/tProduct/list',
    method: 'get',
    params: query
  })
}

export function selectListTProduct(query) {
  return request({
    url: '/shadd/tProduct/selectList',
    method: 'get',
    params: query
  })
}

// 查询产品详细
export function getTProduct (id) {
  return request({
    url: '/shadd/tProduct/' + id,
    method: 'get'
  })
}

// 新增产品
export function addTProduct (data) {
  return request({
    url: '/shadd/tProduct',
    method: 'post',
    data: data
  })
}

// 修改产品
export function updateTProduct (data) {
  return request({
    url: '/shadd/tProduct',
    method: 'put',
    data: data
  })
}

// 删除产品
export function delTProduct (id) {
  return request({
    url: '/shadd/tProduct/' + id,
    method: 'delete'
  })
}

// 导出产品
export function exportTProduct (query) {
  return request({
    url: '/shadd/tProduct/export',
    method: 'get',
    params: query
  })
}
