export const updateTags = ({ commit }, { tags }) => {
  commit("updateTags", { tags });
};

export const addSelectedTags = ({ commit, state }, { tag }) => {
  const selectedTags = state.selectedTags.concat(tag);
  commit("updateSelectedTags", { selectedTags });
};

export const removeSelectedTags = ({ commit, state }, { tag }) => {
  const selectedTags = state.selectedTags.filter(
    (selectedTag) => selectedTag != tag
  );
  commit("updateSelectedTags", { selectedTags });
};

export const addImage = async ({ commit, state }, { image }) => {
  const images = state.images.concat(image);
  commit("updateImages", { images });
};

export const removeImage = ({ commit, state }, { imageIdx }) => {
  const images = state.images.filter((image, idx) => idx != imageIdx);
  commit("updateImages", { images });
};
