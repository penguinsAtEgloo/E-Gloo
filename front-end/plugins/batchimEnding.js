export default (word, ending) => {
  if (typeof word !== "string") return null;

  var lastLetter = word[word.length - 1];
  var uni = lastLetter.charCodeAt(0);

  if (uni < 44032 || uni > 55203) return ending;

  if ((uni - 44032) % 28 == 0) {
    if (ending === "을") return "를";
    else return "는";
  }
  return ending;
};
