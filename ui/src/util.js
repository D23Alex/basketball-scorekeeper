const eq = {
    equalIds(entity1, entity2) {
        if (entity1 === null || entity2 === null)
            return false;
        return (entity1.id === entity2.id);
    }
}

export default eq;