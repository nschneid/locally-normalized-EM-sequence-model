#!/usr/bin/env python2.7
'''
Python implementation of unsupervised tagging evaluation.
cf. edu.cmu.cs.lti.ark.ssl.pos.eval for Java evaluation measures.

Input: Token, gold tag, and predicted (unsupervised) label, tab-separated, 
one token per line, with blank lines separating sequences. 
Currently supports greedy many-to-1 tag alignments. 
Output: Same as input, but with the unsupervised cluster label replaced 
by the mapped tag. Scores and the mapping itself will be written to stderr.

@author: Nathan Schneider (nschneid)
@since: 2012-09-24
'''
from __future__ import print_function, division
import os, sys, re, fileinput, itertools
from collections import Counter

def many2one(c):
    return {pred: max(((g,n) for (g,p),n in c.items() if p==pred), key=lambda x: x[1])[0] for pred in predLabels}
    


# load data from stdin or file arguments
predLabels = set()
data = []
c = Counter()
seq = []
for ln in itertools.chain(fileinput.input(), ['']):
    if not ln.strip():
        if seq:
            data.append(seq)
            seq = []
        continue
    ln = ln.strip()
    t, g, p = ln.split('\t')
    predLabels.add(p)
    seq.append((t,g,p))
    c[(g,p)] += 1

# align the unsupervised cluster labels to the gold tagset
strategy = many2one
mapping = strategy(c)

# compute token accuracy and write output
print('\n'+strategy.__name__, mapping, file=sys.stderr)    
nTotal = nCorrect = 0
for (g,p),n in c.items():
    nTotal += n
    if g==mapping[p]:
        nCorrect += n
print('Accuracy: {}/{}={}'.format(nCorrect, nTotal, nCorrect/nTotal), file=sys.stderr)

for seq in data:
    for t,g,p in seq:
        print(t, g, mapping[p], sep='\t')
    print()
