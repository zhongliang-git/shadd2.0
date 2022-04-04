import request from '@/utils/request'

// 查询配送服务子表列表
export function listTDistributionService (query) {
  return request({
    url: '/shadd/tDistributionService/list',
    method: 'get',
    params: query
  })
}

// 查询配送服务子表详细
export function getTDistributionService (id) {
  return request({
    url: '/shadd/tDistributionService/' + id,
    method: 'get'
  })
}

// 新增配送服务子表
export function addTDistributionService (data) {
  return request({
    url: '/shadd/tDistributionService',
    method: 'post',
    data: data
  })
}

// 修改配送服务子表
export function updateTDistributionService (data) {
  return request({
    url: '/shadd/tDistributionService',
    method: 'put',
    data: data
  })
}

// 删除配送服务子表
export function delTDistributionService (id) {
  return request({
    url: '/shadd/tDistributionService/' + id,
    method: 'delete'
  })
}

// 导出配送服务子表
export function exportTDistributionService (query) {
  return request({
    url: '/shadd/tDistributionService/export',
    method: 'get',
    params: query
  })
}
