import {createSlice} from "@reduxjs/toolkit";

export const reduxSlice = createSlice({
    name: 'redux',
    initialState: {
        currentUser: localStorage.getItem('currentUser'),
        username: localStorage.getItem('username'),
        accessToken: localStorage.getItem('accessToken'),
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
            state.currentUser = undefined
            state.username = undefined
            state.accessToken = undefined
        }
    }
})
export const {login, logout} = reduxSlice.actions
export default reduxSlice.reducer