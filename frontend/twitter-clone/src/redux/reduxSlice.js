import {createSlice} from "@reduxjs/toolkit";

export const reduxSlice = createSlice({
    name: 'redux',
    initialState: {
        currentUser: localStorage.getItem('currentUser'),
        accessToken: localStorage.getItem('token'),
    },
    reducers: {
        login: (state, action) => {
            localStorage.setItem('currentUser', action.payload.userId)
            localStorage.setItem('token', action.payload.accessToken)
            state.currentUser = action.payload.userId
            state.accessToken = action.payload.accessToken
        },
        logout: state => {
            localStorage.removeItem('currentUser')
            localStorage.removeItem('token')
            state.currentUser = undefined
            state.token = undefined
        }
    }
})
export const {login,logout} = reduxSlice.actions
export default reduxSlice.reducer