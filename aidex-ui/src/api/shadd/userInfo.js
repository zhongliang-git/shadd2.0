import request from '@/utils/request'

// 查询用户信息列表
export function listUserInfo (query) {
  return request({
    url: '/shadd/userInfo/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUserInfo (id) {
  return request({
    url: '/shadd/userInfo/' + id,
    method: 'get'
  })
}

// 新增用户信息
export function addUserInfo (data) {
  return request({
    url: '/shadd/userInfo',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUserInfo (data) {
  return request({
    url: '/shadd/userInfo',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUserInfo (id) {
  return request({
    url: '/shadd/userInfo/' + id,
    method: 'delete'
  })
}

// 导出用户信息
export function exportUserInfo (query) {
  return request({
    url: '/shadd/userInfo/export',
    method: 'get',
    params: query
  })
}
