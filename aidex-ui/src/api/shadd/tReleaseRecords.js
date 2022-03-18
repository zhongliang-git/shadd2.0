import request from '@/utils/request'

// 查询发货记录列表
export function listTReleaseRecords (query) {
  return request({
    url: '/shadd/tReleaseRecords/list',
    method: 'get',
    params: query
  })
}

// 查询发货记录详细
export function getTReleaseRecords (id) {
  return request({
    url: '/shadd/tReleaseRecords/' + id,
    method: 'get'
  })
}

// 新增发货记录
export function addTReleaseRecords (data) {
  return request({
    url: '/shadd/tReleaseRecords',
    method: 'post',
    data: data
  })
}

// 修改发货记录
export function updateTReleaseRecords (data) {
  return request({
    url: '/shadd/tReleaseRecords',
    method: 'put',
    data: data
  })
}

// 删除发货记录
export function delTReleaseRecords (id) {
  return request({
    url: '/shadd/tReleaseRecords/' + id,
    method: 'delete'
  })
}

// 导出发货记录
export function exportTReleaseRecords (query) {
  return request({
    url: '/shadd/tReleaseRecords/export',
    method: 'get',
    params: query
  })
}

// 查询厂家列表
export function factoryList (query) {
  return request({
    url: '/shadd/tFactory/factoryList',
    method: 'get',
    params: query
  })
}

// 查询厂家列表
export function productList (query) {
  return request({
    url: '/shadd/purchaseList/purchaseList',
    method: 'get',
    params: query
  })
}
