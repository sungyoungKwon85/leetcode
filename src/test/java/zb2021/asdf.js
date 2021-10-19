var arr = [1,2,'a',3];
for (var i = arr.length -1; i>=0; i--) {
  if (typeof arr[i] !== 'number') {
    arr.splice(i, 1);
  }
}
console.log(arr)