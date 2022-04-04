import request from '@/utils/request'

// 查询邀请码列表
export function listTInvitationCode (query) {
  return request({
    url: '/shadd/tInvitationCode/list',
    method: 'get',
    params: query
  })
}

export function create() {
  return request({
    url: '/shadd/tInvitationCode/create',
    method: 'post'
  })
}

// 查询邀请码详细
export function getTInvitationCode (id) {
  return request({
    url: '/shadd/tInvitationCode/' + id,
    method: 'get'
  })
}

// 新增邀请码
export function addTInvitationCode (data) {
  return request({
    url: '/shadd/tInvitationCode',
    method: 'post',
    data: data
  })
}

// 修改邀请码
export function updateTInvitationCode (data) {
  return request({
    url: '/shadd/tInvitationCode',
    method: 'put',
    data: data
  })
}

// 删除邀请码
export function delTInvitationCode (id) {
  return request({
    url: '/shadd/tInvitationCode/' + id,
    method: 'delete'
  })
}

// 校验邀请码是否存在
export function checkCodeUnique (id, code) {
  if (id === undefined) {
    id = ''
  }
  return request({
    url: '/shadd/tInvitationCode/checkCodeUnique/' + code + '/' + id,
    method: 'get'
  })
}

// 导出邀请码
export function exportTInvitationCode (query) {
  return request({
    url: '/shadd/tInvitationCode/export',
    method: 'get',
    params: query
  })
}
