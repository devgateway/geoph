import { combineReducers } from 'redux'
import language from './language'
import filters from './filters'

const geophApp = combineReducers({
  language,
  filters
})

export default geophApp