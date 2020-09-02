const helper = {
  nullUndefinedOrBlank (value) {
    return value === null || value === undefined || value === ''
  },

  notEmpty (obj) {
    for (const key in obj) {
      if (Object.prototype.hasOwnProperty.call(obj, key)) { return true }
    }
    return false
  },

  arrayEmpty (arr) {
    return arr === undefined || arr.length === 0
  },

  addZeroes(num) {
    let value = Number(num);
    const res = num.split(".");
    if(res.length === 1 || res[1].length < 3) {
      value = value.toFixed(2);
    }
    return value;
  }
}

export default helper
