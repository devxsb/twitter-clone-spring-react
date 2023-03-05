import {createSlice} from "@reduxjs/toolkit";

export const reduxSlice = createSlice({
    name: 'redux',
    initialState: {
        currentUser: localStorage.getItem('currentUser'),
        username: localStorage.getItem('username'),
        accessToken: localStorage.getItem('accessToken'),
        profileImageLink: localStorage.getItem('profileImageLink')
    },
    reducers: {
        login: (state, action) => {
            localStorage.setItem('currentUser', action.payload.userId)
            localStorage.setItem('username', action.payload.username)
            localStorage.setItem('accessToken', action.payload.accessToken)
            state.currentUser = action.payload.userId
            state.username = action.payload.username
            state.accessToken = action.payload.accessToken
        },
        logout: state => {
            localStorage.removeItem('currentUser')
            localStorage.removeItem("username")
            localStorage.removeItem('accessToken')
            localStorage.removeItem('profileImageLink')
            state.currentUser = undefined
            state.username = undefined
            state.accessToken = undefined
            state.profileImageLink = undefined
        },
        setUserDetails: (state, action) => {
            localStorage.setItem('profileImageLink', action.payload.profileImageLink)
            state.profileImageLink = action.payload.profileImageLink
        },
    }
})
export const {login, logout, setUserDetails} = reduxSlice.actions
export default reduxSlice.reducer