import request from '@/utils/request'

// 查询厂家列表
export function listTFactory (query) {
  return request({
    url: '/shadd/tFactory/list',
    method: 'get',
    params: query
  })
}

// 查询厂家详细
export function getTFactory (id) {
  return request({
    url: '/shadd/tFactory/' + id,
    method: 'get'
  })
}

// 新增厂家
export function addTFactory (data) {
  return request({
    url: '/shadd/tFactory',
    method: 'post',
    data: data
  })
}

// 修改厂家
export function updateTFactory (data) {
  return request({
    url: '/shadd/tFactory',
    method: 'put',
    data: data
  })
}

// 删除厂家
export function delTFactory (id) {
  return request({
    url: '/shadd/tFactory/' + id,
    method: 'delete'
  })
}

// 导出厂家
export function exportTFactory (query) {
  return request({
    url: '/shadd/tFactory/export',
    method: 'get',
    params: query
  })
}
