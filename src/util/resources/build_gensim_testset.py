import random
from random import randint
import sys
from gensim.models import Word2Vec

sys.getdefaultencoding()

model_path = "tinyw2v.model"

def build_testset():
    model = Word2Vec.load_word2vec_format(model_path, binary=False, unicode_errors='ignore')

    vocab = list(model.vocab)
    for i in range(1000):
        a = random.sample(vocab, randint(1, 5))
        b = random.sample(vocab, randint(1, 5))
        dist = model.wmdistance(a, b)

        print('{}, {}, {}'.format(' '.join(a), ' '.join(b), dist))


if __name__ == '__main__':
    build_testset()