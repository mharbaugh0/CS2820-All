Channel Analysis for graph: Example4Scheduler Name: Priority
Time Slot	A	B	C	D	E	F	G	H	I	J	K	L	M	N	O	P
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
1	wait(#2)	if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)	wait(#2)	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
2	sleep	wait(#1)	if has(F0) push(F0: C -> D, #1) else pull(F0: B -> C, #1)	wait(#1)	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
3	sleep	sleep	if has(F0) push(F0: C -> D, #2)	wait(#2)	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
4	sleep	wait(#5)	if has(F1) push(F1: C -> B, #5)	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
5	wait(#6)	if has(F1) push(F1: B -> A, #6) else pull(F1: C -> B, #6)	wait(#6)	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
6	wait(#7)	if has(F1) push(F1: B -> A, #7)	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
7	sleep	sleep	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
8	sleep	sleep	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
9	sleep	sleep	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
10	if has(F0) push(F0: A -> B, #3)	wait(#3)	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
11	wait(#4)	if has(F0) push(F0: B -> C, #4) else pull(F0: A -> B, #4)	wait(#4)	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
12	sleep	wait(#3)	if has(F0) push(F0: C -> D, #3) else pull(F0: B -> C, #3)	wait(#3)	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
13	sleep	sleep	if has(F0) push(F0: C -> D, #4)	wait(#4)	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
14	sleep	sleep	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
15	sleep	sleep	sleep	sleep	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null	null
// All flows meet their deadlines
